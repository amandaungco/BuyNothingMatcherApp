package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.google.firebase.auth.FirebaseAuth;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Button signOutButton;
        TextView accountTitle;
        TextView accountEmail;

        String accountEmailString;
        String userEmail;

        userEmail = AppState.INSTANCE.getCurrentUser().getEmail();
        accountEmailString = "Email: " + userEmail;

        accountEmail = findViewById(R.id.myAccountEmail);
        accountEmail.setText(accountEmailString);
        accountEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        accountEmail.setTextColor(Color.BLACK);


        accountTitle = findViewById(R.id.myAccountTitle);
        accountTitle.setText(AppState.INSTANCE.getCurrentUser().getName());
        accountTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        accountTitle.setTextColor(Color.BLACK);


        signOutButton = findViewById(R.id.signout);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                openSignInPage();
                ;

            }
        });
    }

    private void openSignInPage() {
        Intent intent = new Intent(this, FireBaseSignInActivity.class);
        startActivity(intent);
    }
}
