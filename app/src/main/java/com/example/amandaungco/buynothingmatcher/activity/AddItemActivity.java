package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Category;
import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.Item;
import com.example.amandaungco.buynothingmatcher.model.RequestQueueSingleton;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.example.amandaungco.buynothingmatcher.service.ApiCalls;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class AddItemActivity extends AppCompatActivity {

    private final static String TAG = AddItemActivity.class.getSimpleName();

    Spinner categorySpinner;
    Spinner quantitySpinner;
    EditText itemTitleField;
    EditText commentField;
    Button addItemButton;
    Switch requestOrOffer;



    private static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        final RequestQueue buyNothingApiRequestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        categorySpinner = findViewById(R.id.ItemCategory);
        quantitySpinner = findViewById(R.id.ItemQuantity);
        itemTitleField = findViewById(R.id.EditTextItemTitle);
        commentField = findViewById(R.id.EditTextComment);
        commentField.setText("Enter a comment or item description");
        requestOrOffer = findViewById(R.id.requestOrOffer);

        requestOrOffer.setTextOn("Offer"); // displayed text of the Switch whenever it is in checked or on state
        requestOrOffer.setTextOff("Request");

        categorySpinner.setAdapter(new ArrayAdapter<Category>(this,
                android.R.layout.simple_spinner_item, Category.values()));

        String[] arraySpinner = new String[]{
                "1", "2", "3", "4", "5", "6", "7"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);

        addItemButton = findViewById(R.id.AddNewItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postNewItemRequest(createItem(), buyNothingApiRequestQueue);
//                creat eItem();// create hashmap with data from form, have data sent to API
                //with post request, pull up next activtiy and have the activity make the get
                //request from the API to show new object
//                openIndividualUserItemShowPage(type);

            }


        });
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

    private Item createItem() {

        String itemTitle = itemTitleField.getText().toString();
        String itemCategory = categorySpinner.getSelectedItem().toString();
        String itemQuantity = quantitySpinner.getSelectedItem().toString();
        String itemDescription = commentField.getText().toString();
        Boolean isOffer = requestOrOffer.isChecked();

        Item newItem = new Item();
        newItem.setCategory(itemCategory);
//        newItem.setDistance();
        newItem.setQuantity(Integer.parseInt(itemQuantity));
        newItem.setStatus("ACTIVE");// create slider for this
        newItem.setTitle(itemTitle);
        newItem.setDescription(itemDescription);


        if (isOffer) {
            newItem.setType("Offer");
        } else {
            newItem.setType("Request");
        }


        AppState.INSTANCE.setCurrentItem(newItem);
        return newItem;


    }


    private void postNewItemRequest(final Item item, RequestQueue requestQueue) {

        final CompletableFuture<Item> userPostItemRequest = ApiCalls.postNewItemRequest(item, requestQueue);

        List<CompletableFuture<Item>> apiCallFutures = new ArrayList<>();
        apiCallFutures.add(userPostItemRequest);
        final String type;
        type = item.getType();


        CompletableFuture.allOf(apiCallFutures.toArray(new CompletableFuture[apiCallFutures.size()])).thenApply(new Function<Void, Void>() {
            public Void apply(Void o) {

                try {
                    Item userPostNewItem = userPostItemRequest.get();
                    AppState.INSTANCE.setCurrentItem(userPostNewItem);
                    if (type == "offer"){
                            ArrayList<Item> offers = AppState.INSTANCE.getUserOfferItems();
                            offers.add(AppState.INSTANCE.getCurrentItem());
                            AppState.INSTANCE.setUserOfferItems(offers);

                        } else {
                            ArrayList<Item> requests = AppState.INSTANCE.getUserRequestItems();
                            requests.add(AppState.INSTANCE.getCurrentItem());
                            AppState.INSTANCE.setUserRequestItems(requests);
                        }
                        openIndividualUserItemShowPage(type);

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
//    private void postNewItemRequest(Item item) {
//
//
//        RequestQueue itemPostQueue = Volley.newRequestQueue(this);
////break this into two methods, one to create json from firebase user -- firebasetoJSON
//        try {
//            String baseUrl = AppState.getApiURL() + "users/";
//            Long userID;
//            final String type;
//            userID = AppState.INSTANCE.getCurrentUser().getUserId();
//            type = item.getType().toLowerCase();
//            AppState.INSTANCE.getCurrentItem().setType(type);
//            String requestURL = baseUrl + userID + "/" + type + "s";
//
//            JSONObject itemRequestDataBody;
//
//            itemRequestDataBody = Item.convertItemToJson(item);
////            itemRequestDataBody.put("type", type);
//
//            JsonObjectRequest itemDataPostRequest = new JsonObjectRequest(Request.Method.POST, requestURL, itemRequestDataBody, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                    try {
//                        AppState.INSTANCE.setCurrentItem(Item.convertJSONtoItem(response));
//                        if (type == "offer"){
//                            ArrayList<Item> offers = AppState.INSTANCE.getUserOfferItems();
//                            offers.add(AppState.INSTANCE.getCurrentItem());
//                            AppState.INSTANCE.setUserOfferItems(offers);
//
//                        } else {
//                            ArrayList<Item> requests = AppState.INSTANCE.getUserRequestItems();
//                            requests.add(AppState.INSTANCE.getCurrentItem());
//                            AppState.INSTANCE.setUserRequestItems(requests);
//                        }
//                        openIndividualUserItemShowPage(type);
////
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(), "Error:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    if (error !=null) {
//                        Toast.makeText(getApplicationContext(), "Error:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                        error.printStackTrace();
//                    }
//                }
//            });
//            itemPostQueue.add(itemDataPostRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


    //Maybe save to app state instead?
    private void openIndividualUserItemShowPage(String type) {
        Intent intent = new Intent(this, ShowIndividualUsersItemActivity.class);
        intent.putExtra("type", type);
//        intent.putExtra("ItemCategory", newItem.getCategory());
//        intent.putExtra("ItemType", newItem.getType());
//        intent.putExtra("ItemDescription", newItem.getDescription());
//        intent.putExtra("ItemQuantity", newItem.getQuantity());
        startActivity(intent);
    }

}
//
//    private HashMap sendNewRequestToAPI(){
//      create method to do post request
//
//    }
