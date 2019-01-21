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
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Item;
import com.example.amandaungco.buynothingmatcher.model.Match;

import org.json.JSONObject;

import java.util.ArrayList;

public class ShowIndividualUsersItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();


        setContentView(R.layout.activity_show_individual_users_item);
        Item currentItem = AppState.INSTANCE.getCurrentItem();
        String title = currentItem.getTitle();
        String category = currentItem.getCategory();
        String type = intent.getStringExtra("type");
        String description = currentItem.getDescription();
        ArrayList<Match> userItemMatches = AppState.INSTANCE.getCurrentItem().getMatches();
        int quantity = currentItem.getQuantity();

        TextView itemTitleView;
        TextView itemQuantityView;
        TextView itemCategoryView;
        TextView itemDescriptionView;
        TextView matchesHeaderView;
        FloatingActionButton dashboardButton;

        String itemHeader = type.toUpperCase() + " : " + title;
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

        matchesHeaderView = findViewById(R.id.matchesHeader);
        matchesHeaderView.setText(null);

        itemQuantityView = findViewById(R.id.ItemQuantity);
        itemQuantityView.setText(itemQuantity);

        itemCategoryView = findViewById(R.id.itemCategory);
        itemCategoryView.setText(itemCategory);

        itemDescriptionView = findViewById(R.id.itemDescription);
        itemDescriptionView.setText(itemDescription);

        LinearLayout matchesLayout = findViewById(R.id.matchesLayout);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View matchview;


        if (userItemMatches.size() > 0 ) {
            matchesHeaderView.setText(itemMatches);

            for (int i = 0; i < userItemMatches.size(); i++) {

                Match singleUserItemMatch = userItemMatches.get(i);
                matchview = layoutInflater.inflate(R.layout.text_layout, matchesLayout, false);

                // In order to get the view we have to use the new view with text_layout in it
                TextView matchInfo = new TextView(this);
                matchInfo.setText("Match " + singleUserItemMatch.getMatchId() + " : " + currentItem.getTitle());

                // Add the text view to the parent layout
                matchesLayout.addView(matchInfo);
            }
        }


    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
