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
        String comment = getIntent().getExtras().getString("ItemComment");
        String quantity = getIntent().getExtras().getString("ItemQuantity");

        TextView itemTitle;
        TextView itemQuantity;
        TextView itemCategory;
        TextView itemComment;

        String itemHeader = type + " : " + title;

        itemTitle = findViewById(R.id.itemTitle);
        itemTitle.setText(itemHeader);

        itemQuantity = findViewById(R.id.ItemQuantity);
        itemQuantity.setText(quantity);

        itemCategory = findViewById(R.id.itemCategory);
        itemCategory.setText(category);

        itemComment = findViewById(R.id.itemComment);
        itemComment.setText(comment);


    }
}
