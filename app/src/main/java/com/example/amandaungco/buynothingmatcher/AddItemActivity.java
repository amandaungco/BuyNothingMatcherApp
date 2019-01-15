package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.HashMap;

public class AddItemActivity extends AppCompatActivity {

    private final static String TAG = AddItemActivity.class.getSimpleName();

    Spinner categorySpinner;
    Spinner quantitySpinner;
    EditText itemTitleField;
    EditText commentField;
    Button addItemButton;
    Switch requestOrOffer;



    private static final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        categorySpinner = findViewById(R.id.ItemCategory);
        quantitySpinner = findViewById(R.id.ItemQuantity);
        itemTitleField = findViewById(R.id.EditTextItemTitle);
        commentField = findViewById(R.id.EditTextComment);
        commentField.setText("THis is where a placeholder is");
        requestOrOffer =  findViewById(R.id.requestOrOffer);

        requestOrOffer.setTextOn("Offer"); // displayed text of the Switch whenever it is in checked or on state
        requestOrOffer.setTextOff("Request");

        categorySpinner.setAdapter(new ArrayAdapter<Category>(this,
                android.R.layout.simple_spinner_item, Category.values()));

        String[] arraySpinner = new String[]{
                "1", "2", "3", "4", "5", "6", "7"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);

        addItemButton = findViewById(R.id.AddNewItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                creat eItem();// create hashmap with data from form, have data sent to API
                //with post request, pull up next activtiy and have the activity make the get
                //request from the API to show new object
                openIndividualUserItemShowPage(createItem());

            }


        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private HashMap<String, String> createItem() {

        String itemTitle = itemTitleField.getText().toString();
        String itemCategory = categorySpinner.getSelectedItem().toString();
        String itemQuantity = quantitySpinner.getSelectedItem().toString();
        String itemDescription = commentField.getText().toString();
        Boolean isOffer = requestOrOffer.isChecked();

        HashMap<String, String> newItemAttributesToValues = new HashMap<>();


        newItemAttributesToValues.put("title", itemTitle);
        newItemAttributesToValues.put("category", itemCategory);
        newItemAttributesToValues.put("quantity", itemQuantity);

        newItemAttributesToValues.put("description", itemDescription);
        if (isOffer){
            newItemAttributesToValues.put("type", "Offer");
        } else{
            newItemAttributesToValues.put("type", "Request");
        }

        Log.i(TAG, newItemAttributesToValues.toString());

        return newItemAttributesToValues;


    }

    private void openIndividualUserItemShowPage(HashMap<String, String> newItemAttributesToValues) {
        Intent intent = new Intent(this, ShowIndividualItemActivity.class);
        intent.putExtra("ItemTitle", newItemAttributesToValues.get("title"));
        intent.putExtra("ItemCategory", newItemAttributesToValues.get("category"));
        intent.putExtra("ItemType", newItemAttributesToValues.get("type"));
        intent.putExtra("ItemDescription", newItemAttributesToValues.get("description"));
        intent.putExtra("ItemQuantity", newItemAttributesToValues.get("quantity"));
        startActivity(intent);
    }

}
//
//    private HashMap sendNewRequestToAPI(){
//      create method to do post request
//
//    }
