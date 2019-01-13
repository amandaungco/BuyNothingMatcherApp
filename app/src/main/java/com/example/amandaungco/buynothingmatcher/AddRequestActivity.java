package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

public class AddRequestActivity extends AppCompatActivity {

    private final static String TAG = AddRequestActivity.class.getSimpleName();

    Spinner categorySpinner;
    Spinner quantitySpinner;
    EditText requestTitleField;
    EditText commentField;
    Button addRequestButton;


    private static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        categorySpinner = findViewById(R.id.requestCategory);
        quantitySpinner = findViewById(R.id.requestQuantity);
        requestTitleField = findViewById(R.id.EditTextRequestTitle);
        commentField = findViewById(R.id.EditTextComment);

        categorySpinner.setAdapter(new ArrayAdapter<Category>(this,
                android.R.layout.simple_spinner_item, Category.values()));

        String[] arraySpinner = new String[]{
                "1", "2", "3", "4", "5", "6", "7"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);

        addRequestButton = findViewById(R.id.AddNewRequestButton);

        addRequestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createRequest();// create hashmap with data from form, have data sent to API
                //with post request, pull up next activtiy and have the activity make the get
                //request from the API to show new object
                openIndividualRequestPage();

            }


        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private HashMap createRequest() {

        String requestTitle = requestTitleField.getText().toString();
        String requestCategory = categorySpinner.getSelectedItem().toString();
        int requestQuantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
        String requestComment = commentField.getText().toString();

        HashMap<String, String> newRequest = new HashMap<>();

        // Put three keys with values.
        newRequest.put("title", requestTitle);
        newRequest.put("category", requestCategory);
//        newRequest.put("quantity", requestQuantity); // do i have to convert this to stirng or can it stay an integer

        newRequest.put("comment", requestComment);

        Log.i(TAG, newRequest.toString());

        return newRequest;

    }

    private void openIndividualRequestPage() {
        Intent intent = new Intent(this, ShowIndividualRequestActivity.class);
        startActivity(intent);
    }

}
//
//    private HashMap sendNewRequestToAPI(){
//      create method to do post request
//
//    }
