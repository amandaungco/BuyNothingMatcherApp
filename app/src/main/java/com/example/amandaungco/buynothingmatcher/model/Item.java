package com.example.amandaungco.buynothingmatcher.model;

import android.util.Log;
import android.widget.Toast;

import com.example.amandaungco.buynothingmatcher.activity.FireBaseSignInActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class Item {
    private final static String TAG = Item.class.getSimpleName();

    private String category;
    private int distance;
    private int quantity;
    private String title;
    private int itemId;
    private String status;
    private String type;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }



    public static JSONObject convertItemToJson(Item itemForJson)  {

//        try {
            JSONObject itemDataBody = new JSONObject();


            itemDataBody.put("title", itemForJson.getTitle());
            itemDataBody.put("quantity", itemForJson.getQuantity());
            itemDataBody.put("category", itemForJson.getCategory());
            itemDataBody.put("status", itemForJson.getStatus());
            itemDataBody.put("distance", itemForJson.getDistance());
            itemDataBody.put("description", itemForJson.getDescription());
            return itemDataBody;
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(TAG, e.getMessage());
//        }
    }

    public static Item fromJson(JSONObject itemJSONData) throws JSONException { //throws json
        Item newItem = new Item();

        newItem.setDescription(itemJSONData.getString("description"));
//        newItem.setType(itemJSONData.getString("location"));
        newItem.setItemId(itemJSONData.getLong("id"));
        newItem.setCategory(itemJSONData.getString("category"));
        newItem.setCategory(itemJSONData.getString("category"));

        return newUser;


    }
}