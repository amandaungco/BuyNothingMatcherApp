package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RequestsActivity extends AppCompatActivity {

    private FloatingActionButton addRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        addRequestButton = findViewById(R.id.addRequestButton);

        addRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddRequestsPage();
            }
        });

    }

    private void openAddRequestsPage() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}
