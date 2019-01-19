package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class MyAccountActivity extends AppCompatActivity {

    Button signOutButton;
    TextView accountTitle;
    TextView accountEmail;
    TextView accountId;
    LinearLayout myAccountLayout;
    Context context;
    FloatingActionButton dashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        context = getApplicationContext();

        myAccountLayout = findViewById(R.id.myAccountLayout);
        LayoutInflater layoutInflater = getLayoutInflater();
        View myAccountView;

        myAccountView   = layoutInflater.inflate(R.layout.text_layout, myAccountLayout, false);

        createUserInformationView();


        signOutButton = findViewById(R.id.signout);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                openSignInPage();
                ;

            }
        });

        dashboardButton = findViewById(R.id.dashboardButton);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardPage();


            }
        });

    }

    private void createUserInformationView(){
        accountTitle = new TextView(context);
        accountEmail = new TextView(context);
        accountId = new TextView(context);


        String accountEmailString;
        String userEmail;
        String accountTitleString;
        String userName;
        String accountIdString;
        Long userId;
        User currentUser;

        currentUser = AppState.INSTANCE.getCurrentUser();



        userId = currentUser.getUserId();

        userName = currentUser.getName();
        accountTitleString = "Username: " + userName;
        userEmail =currentUser.getEmail();
        accountEmailString = "Email: " + userEmail;
        accountIdString = "Account Number: " + userId;


        accountEmail.setText(accountEmailString);
        accountEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        accountEmail.setTextColor(Color.BLACK);

        accountTitle.setText(accountTitleString);
        accountTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        accountTitle.setTextColor(Color.BLACK);

        accountId.setText(accountIdString);
        accountId.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        accountId.setTextColor(Color.BLACK);

        myAccountLayout.addView(accountTitle);
        myAccountLayout.addView(accountId);
        myAccountLayout.addView(accountEmail);


    }

    private void openSignInPage() {
        Intent intent = new Intent(this, FireBaseSignInActivity.class);
        startActivity(intent);
    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
