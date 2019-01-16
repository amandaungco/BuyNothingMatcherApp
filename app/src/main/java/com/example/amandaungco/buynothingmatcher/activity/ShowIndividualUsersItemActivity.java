package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amandaungco.buynothingmatcher.R;

public class ShowIndividualUsersItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_individual_users_item);
        String title = getIntent().getExtras().getString("ItemTitle");
        String category = getIntent().getExtras().getString("ItemCategory");
        String type = getIntent().getExtras().getString("ItemType");
        String description = getIntent().getExtras().getString("ItemDescription");
        String quantity = getIntent().getExtras().getString("ItemQuantity");

        TextView itemTitleView;
        TextView itemQuantityView;
        TextView itemCategoryView;
        TextView itemDescriptionView;
        TextView matchesHeaderView;
        FloatingActionButton dashboardButton;

        String itemHeader = type + " : " + title;
        String itemQuantity = "Quantity: " + quantity;
        String itemCategory = "Category: " + category;
        String itemDescription = "Description: " + description;
        String itemMatches = "Matches for " + title;

        dashboardButton = findViewById(R.id.dashboardButton);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardPage();
            }
        });

        itemTitleView = findViewById(R.id.itemTitle);
        itemTitleView.setText(itemHeader);

        itemQuantityView = findViewById(R.id.ItemQuantity);
        itemQuantityView.setText(itemQuantity);

        itemCategoryView = findViewById(R.id.itemCategory);
        itemCategoryView.setText(itemCategory);

        itemDescriptionView = findViewById(R.id.itemDescription);
        itemDescriptionView.setText(itemDescription);

        matchesHeaderView = findViewById(R.id.matchesHeader);
        matchesHeaderView.setText(itemMatches);


        LinearLayout matchesLayout = findViewById(R.id.matchesLayout);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View matchview;

        for (int i = 1; i < 10; i++){
            matchview = layoutInflater.inflate(R.layout.text_layout, matchesLayout, false);

            // In order to get the view we have to use the new view with text_layout in it
            TextView matchInfo = new TextView(this);
            matchInfo.setText("Match " + i);

            // Add the text view to the parent layout
            matchesLayout.addView(matchInfo);
        }


    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
