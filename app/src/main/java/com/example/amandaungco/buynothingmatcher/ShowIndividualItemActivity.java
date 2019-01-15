package com.example.amandaungco.buynothingmatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class ShowIndividualItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_individual_item);
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

        String itemHeader = type + " : " + title;
        String itemQuantity = "Quantity: " + quantity;
        String itemCategory = "Category: " + category;
        String itemDescription = "Description: " + description;
        String itemMatches = "Matches for " + title;

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


    }
}
