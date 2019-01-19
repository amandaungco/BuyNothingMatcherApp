package com.example.amandaungco.buynothingmatcher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amandaungco.buynothingmatcher.R;
import com.example.amandaungco.buynothingmatcher.model.AppState;
import com.example.amandaungco.buynothingmatcher.model.User;
import com.example.amandaungco.buynothingmatcher.service.UserService;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    EditText searchEditText;

    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        UserService.getUser(user, this, new FireBaseSignInActivity.OnSuccess());



        System.out.println(user);
        Toast.makeText(DashboardActivity.this, "USER" + user, Toast.LENGTH_SHORT).show();

//        User currentUser = new User();
//
//
//        if (user!= null)   {
//        Toast.makeText(DashboardActivity.this, "USER IS NOT NULL!", Toast.LENGTH_SHORT).show();}
//          currentUser.setName(user.getDisplayName());
//          currentUser.setEmail(user.getEmail());
//
//          AppState.INSTANCE.setCurrentUser(currentUser);



        searchEditText = findViewById(R.id.searchBar);
//        submitButton = findViewById(R.id.submitSearchButton);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    query = searchEditText.getText().toString();
                    openSearchResultsActivity();

//                    submitButton.performClick();
                }
                return false;
            }
        });


//        ImageView swipeImage = findViewById(R.id.itemSwipeImage);
//        swipeImage.setOnClickListener(new onClickListener(View view){
//
//        });{
//                openIndividualItemforSwiping();
//            }



        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_account:
                        openMyAccountPage();

                        break;
                    case R.id.navigation_myitems:
                        openMyItemsPage();
                        break;

                    case R.id.navigation_settings:
                        Toast.makeText(DashboardActivity.this, "Clicked" + menuItem,
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }


        });

    }

    private void openMyItemsPage() {
        Intent intent = new Intent(this, MyItemsActivity.class);
        startActivity(intent);
    }

    private void openMyAccountPage() {
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

    public void openIndividualItemforSwiping(View view) {
        Intent intent = new Intent(this, IndividualItemForSwipingActivity.class);
        startActivity(intent);
    }
    private void openSearchResultsActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }




}


