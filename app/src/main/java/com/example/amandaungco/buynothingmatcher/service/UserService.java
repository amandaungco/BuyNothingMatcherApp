package com.example.amandaungco.buynothingmatcher.service;

import android.arch.core.util.Function;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amandaungco.buynothingmatcher.activity.FireBaseSignInActivity;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserService {

    public interface ApiGetUserCallback{
        void onCallback(User user);
    }


    private final static String TAG = UserService.class.getSimpleName();

    public static void getUser(FirebaseUser user, Context activityContext, final ApiGetUserCallback callback) {

        RequestQueue userGetQueue = Volley.newRequestQueue(activityContext);
        String userEmail;
        userEmail = user.getEmail();


        String url = AppState.getComputerIPUrl();
        String params = "users/?email=";
        url = url + params + userEmail;

        JsonArrayRequest findUserGetRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    User foundUser = User.fromJson(response.getJSONObject(0));
                    callback.onCallback(foundUser);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage());
            }
        });

        userGetQueue.add(findUserGetRequest);


    }



}

