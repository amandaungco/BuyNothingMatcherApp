package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amandaungco.buynothingmatcher.R;

public class IndividualItemForSwipingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_item_for_swiping);

        String title = getIntent().getExtras().getString("ItemTitle");
        String category = getIntent().getExtras().getString("ItemCategory");
        String type = getIntent().getExtras().getString("ItemType");
        String description = getIntent().getExtras().getString("ItemDescription");
        String imageUrl = getIntent().getExtras().getString("ItemImageUrl");
        int quantity = getIntent().getExtras().getInt("ItemQuantity", 1);

//        String title = "Item Title";
//        String category = "Item Category";
//        String type = "Item Type";
//        String description = "Item Description";
//        String quantity = "Item Quantity";
        FloatingActionButton dashboardButton;
        ImageView itemImage;


        TextView itemTitleView;
        TextView itemQuantityView;
        TextView itemCategoryView;
        TextView itemDescriptionView;


        String itemHeader = type + " : " + title;
        String itemQuantity = "Quantity: " + quantity;
        String itemCategory = "Category: " + category;
        String itemDescription = "Description: " + description;

        itemImage = findViewById(R.id.swipingIndiviudalImage);

        Glide.with(this).load(imageUrl).into(itemImage);

        itemTitleView = findViewById(R.id.itemTitle);
        itemTitleView.setText(itemHeader);

        itemQuantityView = findViewById(R.id.ItemQuantity);
        itemQuantityView.setText(itemQuantity);

        itemCategoryView = findViewById(R.id.itemCategory);
        itemCategoryView.setText(itemCategory);

        itemDescriptionView = findViewById(R.id.itemDescription);
        itemDescriptionView.setText(itemDescription);

        dashboardButton = findViewById(R.id.dashboardButton);

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardPage();
            }
        });


    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
