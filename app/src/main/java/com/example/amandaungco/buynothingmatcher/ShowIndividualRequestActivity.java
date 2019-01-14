package com.example.amandaungco.buynothingmatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowIndividualRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_individual_request);

        TextView requestTitle;


        requestTitle = findViewById(R.id.requestTitle);
        requestTitle.setText("Request Title A");

    }
}
