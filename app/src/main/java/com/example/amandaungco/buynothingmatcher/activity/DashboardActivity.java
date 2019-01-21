package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Item;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.example.amandaungco.buynothingmatcher.service.UserService;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    EditText searchEditText;

    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         UserService.getUser(user, this, new UserService.ApiGetUserCallback() {
             @Override
             public void onCallback (User user){
                 AppState.INSTANCE.setCurrentUser(user);
                 getUserItemsRequest();
             }
         });



        System.out.println(user);
        Toast.makeText(DashboardActivity.this, "USER" + user, Toast.LENGTH_SHORT).show();

//        User currentUser = new User();
//
//
//        if (user!= null)   {
//        Toast.makeText(DashboardActivity.this, "USER IS NOT NULL!", Toast.LENGTH_SHORT).show();}
//          currentUser.setName(user.getDisplayName());
//          currentUser.setEmail(user.getEmail());
//
//          AppState.INSTANCE.setCurrentUser(currentUser);



        searchEditText = findViewById(R.id.searchBar);
//        submitButton = findViewById(R.id.submitSearchButton);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    query = searchEditText.getText().toString();
                    openSearchResultsActivity();

//                    submitButton.performClick();
                }
                return false;
            }
        });


//        ImageView swipeImage = findViewById(R.id.itemSwipeImage);
//        swipeImage.setOnClickListener(new onClickListener(View view){
//
//        });{
//                openIndividualItemforSwiping();
//            }



        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_account:
                        openMyAccountPage();

                        break;
                    case R.id.navigation_myitems:
                        openMyItemsPage();
                        break;

                    case R.id.navigation_settings:
                        Toast.makeText(DashboardActivity.this, "Clicked" + menuItem,
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }


        });

    }

    //create method for the gallery, load all items from database except items with user id figure out how to split by offer and request
    //set image from first index in the arraylist from app state for specific type, use on swipe listener
    //swipe right, create match and call the next one
    //swipe left, call the next one
    private void openMyItemsPage() {
        Intent intent = new Intent(this, MyItemsActivity.class);
        startActivity(intent);
    }

    private void openMyAccountPage() {
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

    public void openIndividualItemforSwiping(View view) {
        Intent intent = new Intent(this, IndividualItemForSwipingActivity.class);
        startActivity(intent);
    }
    private void openSearchResultsActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }
    private void getUserItemsRequest() {


        RequestQueue getItemsRequestQueue = Volley.newRequestQueue(this);


        String baseUrl = "http://10.0.2.2:8080/users/";
        Long userID;

        userID = AppState.INSTANCE.getCurrentUser().getUserId();
//        AppState.INSTANCE.getNewItem().setType(type);

        ArrayList<String> itemTypes = new ArrayList<>();
        itemTypes.add("offers");
        itemTypes.add("requests");


        for (int i = 0; i < itemTypes.size(); i++) {
            final String itemType = itemTypes.get(i);
            String requestURL = baseUrl + userID + "/" + itemType;

            JsonArrayRequest itemDataGetRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        ArrayList<Item> items = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject singleJSONItem = response.getJSONObject(i);
                            items.add(Item.convertJSONtoItem(singleJSONItem));
                        }
                        if ("offers" == itemType){
                            AppState.INSTANCE.setUserOfferItems(items);
                        } else{
                            AppState.INSTANCE.setUserRequestItems(items);
                        }
//
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error != null) {
                        Toast.makeText(getApplicationContext(), "Error:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
            });
            getItemsRequestQueue.add(itemDataGetRequest);
        }
    }




}


