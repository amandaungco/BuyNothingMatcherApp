package com.example.amandaungco.buynothingmatcher.model;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AppState {
    //singleton

    final public static AppState INSTANCE = new AppState();
    private User currentUser;
    private Item newItem;
    private ArrayList <Item> userOfferItems;
    private ArrayList <Item> userRequestItems;


    public ArrayList<Item> getUserOfferItems() {
        return userOfferItems;
    }

    public void setUserOfferItems(ArrayList<Item> userOfferItems) {
        this.userOfferItems = userOfferItems;
    }

    public ArrayList<Item> getUserRequestItems() {
        return userRequestItems;
    }

    public void setUserRequestItems(ArrayList<Item> userRequestItems) {
        this.userRequestItems = userRequestItems;
    }

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
