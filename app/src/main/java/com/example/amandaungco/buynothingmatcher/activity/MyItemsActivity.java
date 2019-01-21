package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Item;

import java.util.ArrayList;


public class MyItemsActivity extends AppCompatActivity {

    private Button addItemButton;
    private FloatingActionButton dashboardButton;
    CardView itemCardView;
    Context context;
    GridLayout.LayoutParams layoutparams;
    TextView itemCardTextView;
    GridLayout myItemsGridLayout;
    GridLayout requestItemsGridLayout;
    GridLayout offerItemsGridlayout;
    Switch requestOrOffer;
    ArrayList<Item> userItems;
    Item singleItem;
    String type;
    GridLayout populateGridLayout;
    Boolean isOffer;

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

        context = getApplicationContext();

        dashboardButton = findViewById(R.id.dashboardButton);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardPage();
            }
        });

        requestOrOffer = findViewById(R.id.requestOrOffer);
        isOffer = requestOrOffer.isChecked();
        offerItemsGridlayout = findViewById(R.id.gridOfferItemsLayout);
        requestItemsGridLayout = findViewById(R.id.gridRequestItemsLayout);


        requestOrOffer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isOffer) {

                if (isOffer) {

                    requestItemsGridLayout.setVisibility(View.INVISIBLE);
                    offerItemsGridlayout.setVisibility(View.VISIBLE);

                } else {
                    offerItemsGridlayout.setVisibility(View.INVISIBLE);
                    requestItemsGridLayout.setVisibility(View.VISIBLE);

                }
            }
        });

        populateMyItemsGrid();

    }


    private void populateMyItemsGrid() {


        for (int j = 0; j < 2; j++) {
            if (j == 0) {
                userItems = AppState.INSTANCE.getUserOfferItems();
                myItemsGridLayout = findViewById(R.id.gridOfferItemsLayout);
                type = "Offer";
            } else {
                userItems = AppState.INSTANCE.getUserRequestItems();
                myItemsGridLayout = findViewById(R.id.gridRequestItemsLayout);
                type = "Request";
            }

            LayoutInflater layoutInflater = getLayoutInflater();
            View myItemsView;
            if (userItems != null) {
                for (int i = 0; i < userItems.size(); i++) {
                    singleItem = userItems.get(i);

                    myItemsView = layoutInflater.inflate(R.layout.text_layout, myItemsGridLayout, false);

                    // In order to get the view we have to use the new view with text_layout in it
                    createCardItem(singleItem, type, myItemsGridLayout);

                    // Add the text view to the parent layout
//            myItemsGridLayout.addView();
                }

            }
        }
    }



    private void openAddNewItemPage() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    private void createCardItem(Item singleItem, String type, GridLayout populateGridLayout) {


        itemCardView = new CardView(context);

        layoutparams = new GridLayout.LayoutParams();
        layoutparams.height = 200;
        layoutparams.width = 450;
        layoutparams.setMargins(25, 25, 25, 25);

        itemCardView.setLayoutParams(layoutparams);

        itemCardView.setRadius(15);
        itemCardView.setId(singleItem.getItemId());

        itemCardView.setPadding(100, 100, 100, 100);

        itemCardView.setCardElevation(8);
        itemCardView.setCardBackgroundColor(Color.argb(255, 0, 133, 119));
        itemCardView.setClickable(true);

        setOnClick(itemCardView, singleItem, type);


        itemCardTextView = new TextView(context);

        itemCardTextView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        itemCardTextView.setText(type + singleItem.getItemId() + " : " + singleItem.getTitle());
        itemCardTextView.setGravity(Gravity.CENTER);
        itemCardTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        itemCardTextView.setTextColor(Color.WHITE);

//        itemCardTextView.setPadding(25,25,25,25);

        itemCardTextView.setGravity(Gravity.CENTER);

        itemCardView.addView(itemCardTextView);

        populateGridLayout.addView(itemCardView);

    }


    private void openIndiviudalUserItemspage() {

        Intent intent = new Intent(this, ShowIndividualUsersItemActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private void setOnClick( final CardView itemCardView, final Item singleItem, final String type) {
        itemCardView.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppState.INSTANCE.setCurrentItem(AppState.INSTANCE
                        .findCurrentItem(singleItem.getItemId(), type));
                openIndiviudalUserItemspage();


            }
        });

    }



}

