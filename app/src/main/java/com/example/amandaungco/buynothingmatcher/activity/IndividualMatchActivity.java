package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.Item;
import com.example.amandaungco.buynothingmatcher.model.Match;

import java.util.ArrayList;

public class IndividualMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_match);

        Intent intent = getIntent();
        FloatingActionButton dashboardButton;

        setContentView(R.layout.activity_individual_match);
        Match currentMatch = AppState.INSTANCE.getCurrentMatch();
        Item currentItem = AppState.INSTANCE.getCurrentItem();
        String title = currentItem.getTitle();
        String category = currentItem.getCategory();
//
        TextView matchTitleView;
        TextView matchCategoryView;
        TextView matchRequesterView;
        TextView matchOffererView;
        TextView matchDistance;
        Button createExchangeButton;
        int requesterName;
        int offererName;

        requesterName = currentMatch.getRequestId();
        matchRequesterView = findViewById(R.id.matchRequesterName);
        matchRequesterView.setText("Request ID:" + requesterName);

        offererName = currentMatch.getOfferId();
        matchOffererView = findViewById(R.id.matchOffererName);
        matchOffererView.setText("Offer ID: " + offererName);


        String matchHeader = "Match for " + title;

        String matchCategory = "Category: " + category;

        dashboardButton = findViewById(R.id.dashboardButton);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardPage();
            }
        });

        matchTitleView = findViewById(R.id.matchTitle);
        matchTitleView.setText(matchHeader);



        matchCategoryView = findViewById(R.id.matchCategory);
        matchCategoryView.setText(matchCategory);

    }

    private void openDashboardPage() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);

    }

}
