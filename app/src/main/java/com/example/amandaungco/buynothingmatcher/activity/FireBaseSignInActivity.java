package com.example.amandaungco.buynothingmatcher.activity;

import android.arch.core.util.Function;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.example.amandaungco.buynothingmatcher.service.UserService;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.FileVisitResult;
import java.util.Arrays;
import java.util.List;

// need to figure out where the already signed in user is checked so i can get that user's info from the API and save it to the sharedpreferences or app state

public class FireBaseSignInActivity extends AppCompatActivity {

    private final static String TAG = FireBaseSignInActivity.class.getSimpleName();

    int RC_SIGN_IN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_sign_in);

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());
//
// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

    }

    private void postNewUserRequest(FirebaseUser user) {

        RequestQueue userPostQueue = Volley.newRequestQueue(this);
//break this into two methods, one to create json from firebase user -- firebasetoJSON
        try {
            String url = AppState.getApiURL() + "users/";

            JSONObject userDataBody = new JSONObject();

            userDataBody.put("email", user.getEmail());
            userDataBody.put("name", user.getDisplayName());

            JsonObjectRequest userDataPostRequest = new JsonObjectRequest(Request.Method.POST, url, userDataBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();

                    try {
                        AppState.INSTANCE.setCurrentUser(User.fromJson(response));
                        openDashboardPage();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            });
            userPostQueue.add(userDataPostRequest);
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (idpResponse.isNewUser()) {
                    postNewUserRequest(user);
                } else {
                    UserService.getUser(user, this, new UserService.ApiGetUserCallback() {
                        @Override
                        public void onCallback(User user) {
                            AppState.INSTANCE.setCurrentUser(user);
                            openDashboardPage();
                        }
                    });
                }
            }
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}






