package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_account:
                        Toast.makeText(DashboardActivity.this, "Clicked" + menuItem,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_myitems:
                        openMyItemsPage();
                        break;

                    case R.id.navigation_settings:
                        Toast.makeText(DashboardActivity.this, "Clicked" + menuItem,
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }


        });

    }

    private void openMyItemsPage() {
        Intent intent = new Intent(this, MyItemsActivity.class);
        startActivity(intent);
    }


}


