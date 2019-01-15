package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    EditText searchEditText;
    Button submitButton;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        searchEditText = findViewById(R.id.searchBar);
//        submitButton = findViewById(R.id.submitSearchButton);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    query = searchEditText.getText().toString();
                    openSearchResultsActivity();

//                    submitButton.performClick();
                }
                return false;
            }
        });


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
    private void openSearchResultsActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }



}


