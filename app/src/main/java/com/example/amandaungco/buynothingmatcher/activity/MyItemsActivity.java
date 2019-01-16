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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;


public class MyItemsActivity extends AppCompatActivity {

    private Button addItemButton;
    private FloatingActionButton dashboardButton;
    CardView itemCardView;
    Context context;
    GridLayout.LayoutParams layoutparams;
    TextView itemCardTextView;
    GridLayout myItemsGridLayout;

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


//        getRequestMyItems();


        myItemsGridLayout = findViewById(R.id.gridItemsLayout);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View myItemsView;

        for (int i = 1; i < 10; i++){
            myItemsView = layoutInflater.inflate(R.layout.text_layout, myItemsGridLayout, false);

            // In order to get the view we have to use the new view with text_layout in it
            createCardItem(i);

            // Add the text view to the parent layout
//            myItemsGridLayout.addView();
        }



    }

//    private void getRequestMyItems() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "localhost:8080/";
            int userId = AppState.INSTANCE.getCurrentUser().getUserId();
//
//        // https://developer.android.com/training/volley/requestqueue#singleton
//        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                textView.setText("Response is: " + response.substring(0, 500));
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText(error.getMessage());
//            }
//        });
//        queue.add(strReq);
//
//        gridView.setAdapter(new FooAdapter(this, new ArrayList<String>() {{ add("Hello!"); add("World!"); }}));
//    }


    private void openAddNewItemPage() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    private void createCardItem(int i) {


        itemCardView = new CardView(context);

        layoutparams = new GridLayout.LayoutParams();
        layoutparams.height = 200;
        layoutparams.width = 450;
        layoutparams.setMargins(25,25,25,25);

        itemCardView.setLayoutParams(layoutparams);

        itemCardView.setRadius(15);

        itemCardView.setPadding(100, 100, 100, 100);

        itemCardView.setCardElevation(8);
        itemCardView.setCardBackgroundColor(Color.argb(255, 0, 133,119 ));

        itemCardTextView = new TextView(context);

        itemCardTextView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        itemCardTextView.setText("item:" + i);
        itemCardTextView.setGravity(Gravity.CENTER);
        itemCardTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

        itemCardTextView.setTextColor(Color.WHITE);

//        itemCardTextView.setPadding(25,25,25,25);

        itemCardTextView.setGravity(Gravity.CENTER);

        itemCardView.addView(itemCardTextView);

        myItemsGridLayout.addView(itemCardView);

    }





}
