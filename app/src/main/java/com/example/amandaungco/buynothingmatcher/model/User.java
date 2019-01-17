package com.example.amandaungco.buynothingmatcher.model;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String location;
    private String name;
    private int userId;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static User fromJson(JSONObject userJSONData) throws JSONException { //throws json
        User newUser = new User();


        newUser.setName(userJSONData.getString("name"));
        newUser.setLocation(userJSONData.getString("location"));
        newUser.setUserId(userJSONData.getInt("id"));
        newUser.setEmail(userJSONData.getString("email"));


        return newUser;


    }


}

