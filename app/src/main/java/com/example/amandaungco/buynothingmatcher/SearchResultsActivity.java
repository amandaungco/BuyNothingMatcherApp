package com.example.amandaungco.buynothingmatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {

    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        String query = getIntent().getExtras().getString("query");

        final TextView searchResultsHeader = findViewById(R.id.searchResultsHeader);
        searchResultsHeader.setText("# results found for " + query);

        searchEditText = findViewById(R.id.searchBar);
//        submitButton = findViewById(R.id.submitSearchButton);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                   String query = searchEditText.getText().toString();
                    searchResultsHeader.setText("# results found for " + query);
                    //query API
//                    submitButton.performClick();
                }
                return false;
            }
        });

    }
}
