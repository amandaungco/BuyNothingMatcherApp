package com.example.amandaungco.buynothingmatcher.service;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Item;
import com.example.amandaungco.buynothingmatcher.model.RequestQueueSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ApiCalls {

    public static CompletableFuture<ArrayList<Item>> getUserItemsCall(RequestQueue buyNothingApiRequestQueue, Boolean isOffer) {


        String baseUrl = AppState.getApiURL() + "users/";
        Long userID;

        userID = AppState.INSTANCE.getCurrentUser().getUserId();
        String requestURL;

        if (isOffer) {
            requestURL = baseUrl + userID + "/offers";

        } else {
            requestURL = baseUrl + userID + "/requests";
        }


        final CompletableFuture<ArrayList<Item>> itemsFromJson = new CompletableFuture<>();

        JsonArrayRequest itemDataGetRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Item> items = new ArrayList<>();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        Object singleItem = response.get(i);
                        if (!(singleItem instanceof JSONObject)) {
                            continue;
                        }
                        JSONObject singleJSONItem = (JSONObject) singleItem;
                        items.add(Item.convertJSONtoItem(singleJSONItem));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                itemsFromJson.complete(items);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    error.printStackTrace();
                    itemsFromJson.complete(new ArrayList<Item>());
                }
            }
        });
        buyNothingApiRequestQueue.add(itemDataGetRequest);
        return itemsFromJson;
    }

    public static CompletableFuture<ArrayList<Item>> getDBItemsCall(RequestQueue buyNothingApiRequestQueue, Boolean isOffer) {


        String baseUrl = AppState.getApiURL();

        String requestURL;

        if (isOffer) {
            requestURL = baseUrl + "/offers";

        } else {
            requestURL = baseUrl + "/requests";
        }


        final CompletableFuture<ArrayList<Item>> itemsFromJson = new CompletableFuture<>();

        JsonObjectRequest itemDataGetRequest = new JsonObjectRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<Item> items = new ArrayList<>();

                try {
                    JSONArray itemsFromJSONArray;
                    itemsFromJSONArray = response.getJSONArray("content");

                    for (int i = 0; i < itemsFromJSONArray.length(); i++) {
                        Object singleItem = itemsFromJSONArray.get(i);
                        if (!(singleItem instanceof JSONObject)) {
                            continue;
                        }
                        JSONObject singleJSONItem = (JSONObject) singleItem;
                        items.add(Item.convertJSONtoItem(singleJSONItem));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                itemsFromJson.complete(items);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    error.printStackTrace();
                    itemsFromJson.complete(new ArrayList<Item>());
                }
            }
        });
        buyNothingApiRequestQueue.add(itemDataGetRequest);
        return itemsFromJson;
    }
}
