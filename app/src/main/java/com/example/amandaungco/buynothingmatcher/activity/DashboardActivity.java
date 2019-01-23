package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Card;
import com.example.amandaungco.buynothingmatcher.model.Item;
import com.example.amandaungco.buynothingmatcher.model.RequestQueueSingleton;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.example.amandaungco.buynothingmatcher.model.arrayAdapter;
import com.example.amandaungco.buynothingmatcher.service.ApiCalls;
import com.example.amandaungco.buynothingmatcher.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class DashboardActivity extends AppCompatActivity {

    EditText searchEditText;
    String query;
    List<Card> rowItems;
    ListView listview;
    Switch requestOrOffer;
    Boolean isOffer;
    ArrayList<Item> itemsForCards;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final RequestQueue buyNothingApiRequestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserService.getUser(user, this, new UserService.ApiGetUserCallback() {
                @Override
                public void onCallback(User user) {
                    AppState.INSTANCE.setCurrentUser(user);
                    getItemsRequest(buyNothingApiRequestQueue);

                }
            });
        } else {
            openLoginPage();
            return;
        }


        requestOrOffer = findViewById(R.id.requestOrOffer);
        isOffer = requestOrOffer.isChecked();
        requestOrOffer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isOffer) {

                if (isOffer) {
                    itemsForCards = AppState.INSTANCE.getAllOfferDBItems();

                } else {
                    itemsForCards = AppState.INSTANCE.getAllOfferDBItems();

                }
                createCardsforGallery(itemsForCards, isOffer);
            }
        });


        rowItems = new ArrayList<>();
        createStarterCardsforGallery();


        searchEditText = findViewById(R.id.searchBar);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    query = searchEditText.getText().toString();
                    openSearchResultsActivity();
                }
                return false;
            }
        });


        final arrayAdapter arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.swipeFrame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

//                Card obj = (Card) dataObject;
//                String userId = obj.getUserId();
//                usersDb.child(userId).child("connections").child("nope").child(currentUId).setValue(true);
                Toast.makeText(DashboardActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                //match match
//                cards obj = (cards) dataObject;
//                String userId = obj.getUserId();
//                usersDb.child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
//                isConnectionMatch(userId);
                Toast.makeText(DashboardActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                //pass information to next activity
                openIndividualItemforSwiping();
            }
        });


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


    private void openMyItemsPage() {
        Intent intent = new Intent(this, MyItemsActivity.class);
        startActivity(intent);
    }

    private void openLoginPage() {
        Intent intent = new Intent(this, FireBaseSignInActivity.class);
        startActivity(intent);
    }

    private void openMyAccountPage() {
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

    public void openIndividualItemforSwiping() {
        Intent intent = new Intent(this, IndividualItemForSwipingActivity.class);
        startActivity(intent);
    }

    private void openSearchResultsActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

//    private void makeAPICalls(){
//        getAlltemsRequest();
//        getUserItemsRequest();
//    }


    private void getItemsRequest(RequestQueue requestQueue) {

        final CompletableFuture<ArrayList<Item>> userOfferItemsRequest = ApiCalls.getUserItemsCall(requestQueue, true);
        final CompletableFuture<ArrayList<Item>> userRequestItemsRequest = ApiCalls.getUserItemsCall(requestQueue, false);

        final CompletableFuture<ArrayList<Item>> dbOfferItemsRequest = ApiCalls.getDBItemsCall(requestQueue, true);
        final CompletableFuture<ArrayList<Item>> dbRequestItemsRequest = ApiCalls.getDBItemsCall(requestQueue, false);

        List<CompletableFuture<ArrayList<Item>>> apiCallFutures = new ArrayList<>();
        apiCallFutures.add(userOfferItemsRequest);
        apiCallFutures.add(userRequestItemsRequest);
        apiCallFutures.add(dbOfferItemsRequest);
        apiCallFutures.add(dbRequestItemsRequest);

        CompletableFuture.allOf(apiCallFutures.toArray(new CompletableFuture[apiCallFutures.size()])).thenApply(new Function<Void, Void>() {
            public Void apply(Void o) {
                // now we know both the futures are completed
                // here we can safely call .get on the future!

                try {
                    ArrayList<Item> userOfferItems = userOfferItemsRequest.get();
                    ArrayList<Item> userRequestItems = userRequestItemsRequest.get();
                    ArrayList<Item> dbRequestItems = dbRequestItemsRequest.get();
                    ArrayList<Item> dbOfferItems = dbOfferItemsRequest.get();
                    AppState.INSTANCE.setUserOfferItems(userOfferItems);
                    AppState.INSTANCE.setUserRequestItems(userRequestItems);
                    AppState.INSTANCE.setAllOfferDBItems(dbOfferItems);
                    AppState.INSTANCE.setAllRequestDBItems(dbRequestItems);
                    createCardsforGallery(dbOfferItems, true);
                    createCardsforGallery(dbRequestItems, false);

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });








    }
//    public CompletableFuture<ArrayList<Item>>
//
//        RequestQueue buyNothingApiRequestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();
//
//        String baseUrl = AppState.getApiURL();
//        Long userID;
//
//        userID = AppState.INSTANCE.getCurrentUser().getUserId();
////        AppState.INSTANCE.getNewItem().setType(type);
//
//        ArrayList<String> itemTypes = new ArrayList<>();
//        itemTypes.add("offers");
//        itemTypes.add("requests");
//
//
//        for (int i = 0; i < itemTypes.size(); i++) {
//            final String itemType = itemTypes.get(i);
//            String requestURL = baseUrl + "users/" + userID + "/" + itemType;
//
//            JsonArrayRequest itemDataGetRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    try {
//                        ArrayList<Item> items = new ArrayList<>();
//
//                        for (int i = 0; i < response.length(); i++) {
//                            Object singleItem = response.get(i);
//                            if (singleItem instanceof Integer) {
//                                continue;
//                            }
//                            JSONObject singleJSONItem = (JSONObject) singleItem;
//                            items.add(Item.convertJSONtoItem(singleJSONItem));
//                        }
//                        if ("offers".equals(itemType)) {
//                            AppState.INSTANCE.setUserOfferItems(items);
//                        } else {
//                            AppState.INSTANCE.setUserRequestItems(items);
//                        }
////
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(), "Error:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    if (error != null) {
//                        Toast.makeText(getApplicationContext(), "Error:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                        error.printStackTrace();
//                    }
//                }
//            });
//            buyNothingApiRequestQueue.add(itemDataGetRequest);
//        }
//
//    }

//    private void getAlltemsRequest() {
//
//
//        RequestQueue buyNothingApiRequestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();
//
//        String baseUrl = AppState.getApiURL();
////        Long userID;
////
////        userID = AppState.INSTANCE.getCurrentUser().getUserId();
////        AppState.INSTANCE.getNewItem().setType(type);
//
//        ArrayList<String> itemTypes = new ArrayList<>();
//        itemTypes.add("offers/");
//        itemTypes.add("requests/");
//
//
//        for (int i = 0; i < itemTypes.size(); i++) {
//            final String itemType = itemTypes.get(i);
//            String requestURL = baseUrl + itemType;

//            JsonObjectRequest itemDataGetRequest = new JsonObjectRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {


//                    try {
//                        JSONArray items;
//                        items = response.getJSONArray("content");
//                        ArrayList<Item> itemsfromDb = new ArrayList<>();
//
//                        for (int i = 0; i < items.length(); i++) {
//                            Object singleItem = items.get(i);
//                            if (singleItem instanceof Integer) {
//                                continue;
//                            }
//                            JSONObject singleJSONItem = (JSONObject) singleItem;
//                            itemsfromDb.add(Item.convertJSONtoItem(singleJSONItem));
//                        }
//                        if ("offers/".equals(itemType)) {
//                            AppState.INSTANCE.setAllOfferDBItems(itemsfromDb);
//                            Toast.makeText(DashboardActivity.this, "OFFERS LOADED " + itemsfromDb.size(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            AppState.INSTANCE.setAllRequestDBItems(itemsfromDb);
//                            Toast.makeText(DashboardActivity.this, "ALl DB Requests Loaded to appState " + itemsfromDb.size(), Toast.LENGTH_SHORT).show();
//                        }
////
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(), "Error:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    if (error != null) {
//                        Toast.makeText(getApplicationContext(), "Error:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                        error.printStackTrace();
//                    }
//                }
//            });
//            buyNothingApiRequestQueue.add(itemDataGetRequest);
//        }
//
//    }

    public void createCardsforGallery(ArrayList<Item> itemsForCards, Boolean isOffer) {
        Item singleItemforCard;

        for (int i = 0; i < itemsForCards.size(); i++) {
            singleItemforCard = itemsForCards.get(i);
            Card itemCard = new Card(singleItemforCard.getItemId(), singleItemforCard.getTitle(), isOffer);
            rowItems.add(itemCard);
        }
    }

    public void createStarterCardsforGallery() {
        ArrayList<String> testvalues = new ArrayList<>();
        testvalues.add("Test 1");
//        testvalues.add("test 2");
//        testvalues.add("test 3");
//        testvalues.add("test 4");
//        testvalues.add("test 5");
//        testvalues.add("test 6");

        for (int i = 0; i < testvalues.size(); i++) {
            Card itemCard = new Card(i, testvalues.get(i), true);
            rowItems.add(itemCard);
        }

    }


}


