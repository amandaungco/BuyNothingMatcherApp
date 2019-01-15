package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyItemsActivity extends AppCompatActivity {

    private Button addItemButton;
    private FloatingActionButton dashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        addItemButton = findViewById(R.id.AddNewItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewItemPage();
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

    private void openAddNewItemPage() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
