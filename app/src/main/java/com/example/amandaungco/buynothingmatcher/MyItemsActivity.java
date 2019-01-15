package com.example.amandaungco.buynothingmatcher;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyItemsActivity extends AppCompatActivity {

    private FloatingActionButton addItemButton;

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

    }

    private void openAddNewItemPage() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}
