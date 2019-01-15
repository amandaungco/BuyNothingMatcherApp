package com.example.amandaungco.buynothingmatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        String query = getIntent().getExtras().getString("query");

        TextView searchResultsHeader = findViewById(R.id.searchResultsHeader);
        searchResultsHeader.setText("# results found for " + query);

    }
}
