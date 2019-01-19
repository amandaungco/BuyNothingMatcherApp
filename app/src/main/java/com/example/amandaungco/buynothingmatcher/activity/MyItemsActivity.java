package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyItemsActivity extends AppCompatActivity {

    private Button addItemButton;
    private FloatingActionButton dashboardButton;
    CardView itemCardView;
    Context context;
    GridLayout.LayoutParams layoutparams;
    TextView itemCardTextView;
    GridLayout myItemsGridLayout;
    Switch requestOrOffer;
    ArrayList<Item> userItems;
    Item singleItem;

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
        Boolean isOffer = requestOrOffer.isChecked();
        final String type;

        if (isOffer) {
            type = "offers";
        } else {
            type = "requests";
        }

        getUserItemsRequest(type);
        userItems = AppState.INSTANCE.getUserItems();


        myItemsGridLayout = findViewById(R.id.gridItemsLayout);

        LayoutInflater layoutInflater = getLayoutInflater();
        View myItemsView;
        if (userItems != null) {
            for (int i = 0; i < userItems.size(); i++) {
                singleItem = userItems.get(i);

                myItemsView = layoutInflater.inflate(R.layout.text_layout, myItemsGridLayout, false);

                // In order to get the view we have to use the new view with text_layout in it
                createCardItem(singleItem);

                // Add the text view to the parent layout
//            myItemsGridLayout.addView();
            }
        }


    }

    private void getUserItemsRequest(String type) {


        RequestQueue getItemsRequestQueue = Volley.newRequestQueue(this);


        String baseUrl = "http://10.0.2.2:8080/users/";
        Long userID;

        userID = AppState.INSTANCE.getCurrentUser().getUserId();
//        AppState.INSTANCE.getNewItem().setType(type);
        String requestURL = baseUrl + userID + "/" + type;

        JsonArrayRequest itemDataGetRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    ArrayList<Item> items = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject singleJSONItem = response.getJSONObject(i);
                        items.add(Item.convertJSONtoItem(singleJSONItem));
                    }
                    AppState.INSTANCE.setUserItems(items);
//
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error:  " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    Toast.makeText(getApplicationContext(), "Error:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }
        });
        getItemsRequestQueue.add(itemDataGetRequest);
    }


    private void openAddNewItemPage() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    private void createCardItem(Item item) {


        itemCardView = new CardView(context);

        layoutparams = new GridLayout.LayoutParams();
        layoutparams.height = 200;
        layoutparams.width = 450;
        layoutparams.setMargins(25, 25, 25, 25);

        itemCardView.setLayoutParams(layoutparams);

        itemCardView.setRadius(15);

        itemCardView.setPadding(100, 100, 100, 100);

        itemCardView.setCardElevation(8);
        itemCardView.setCardBackgroundColor(Color.argb(255, 0, 133, 119));

        itemCardTextView = new TextView(context);

        itemCardTextView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        itemCardTextView.setText("item" + item.getItemId() + " : " + item.getTitle());
        itemCardTextView.setGravity(Gravity.CENTER);
        itemCardTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        itemCardTextView.setTextColor(Color.WHITE);

//        itemCardTextView.setPadding(25,25,25,25);

        itemCardTextView.setGravity(Gravity.CENTER);

        itemCardView.addView(itemCardTextView);

        myItemsGridLayout.addView(itemCardView);

    }


}
