package com.example.amandaungco.buynothingmatcher.model;

import com.google.firebase.auth.FirebaseUser;

public class AppState {
    //singleton

    final public static AppState INSTANCE = new AppState();
    private User currentUser;
    private Item newItem;

    public Item getNewItem() {
        return newItem;
    }

    public void setNewItem(Item newItem) {
        this.newItem = newItem;
    }

//
//    public FirebaseUser getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(FirebaseUser currentUser) {
//        this.currentUser = currentUser;
//    }
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private AppState() {


    }
}
