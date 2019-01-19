package com.example.amandaungco.buynothingmatcher.model;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AppState {
    //singleton

    final public static AppState INSTANCE = new AppState();
    private User currentUser;
    private Item newItem;
    private ArrayList <Item> userItems;


    public ArrayList<Item> getUserItems() {
        return userItems;
    }

    public void setUserItems(ArrayList<Item> userItems) {
        this.userItems = userItems;
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
