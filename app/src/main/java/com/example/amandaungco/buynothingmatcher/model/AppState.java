package com.example.amandaungco.buynothingmatcher.model;

import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppState {
    //singleton

    final public static AppState INSTANCE = new AppState();
    private User currentUser;
    private Item currentItem;
    private Item newItem;
    private ArrayList<Item> userOfferItems;
    private ArrayList<Item> userRequestItems;
    private Match currentMatch;
    private ArrayList<Item> allRequestDBItems;
    private ArrayList<Item> allOfferDBItems;

    public ArrayList<Item> getAllRequestDBItems() {
        return allRequestDBItems;
    }

    public void setAllRequestDBItems(ArrayList<Item> allRequestDBItems) {
        this.allRequestDBItems = allRequestDBItems;
    }

    public ArrayList<Item> getAllOfferDBItems() {
        return allOfferDBItems;
    }

    public void setAllOfferDBItems(ArrayList<Item> allOfferDBItems) {
        this.allOfferDBItems = allOfferDBItems;
    }

    private static final String emulatorUrl = "http://10.0.2.2:8080/";
    private static final String computerIPUrl = "http://192.168.5.85:8080/";

    public static String getEmulatorUrl() {
        return emulatorUrl;
    }

    public static String getComputerIPUrl() {
        return computerIPUrl;
    }


    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }


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

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
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

    public Item findCurrentItem(int itemID, String itemType) {
        ArrayList<Item> searchItems;
        Item singleItem;
        if (itemType == "Offer") {
            searchItems = userOfferItems;
        } else {
            searchItems = userRequestItems;
        }

        for (int i = 0; i < searchItems.size(); i++) {
            singleItem = searchItems.get(i);
            if (singleItem.getItemId() == itemID) {
                return singleItem;
            }
        }

        return null;
    }

    public Match findCurrentMatch(int matchId, Item currentItem) {
        ArrayList<Match> searchMatches = currentItem.getMatches();
        Match singleMatch;

        for (int i = 0; i < searchMatches.size(); i++) {
            singleMatch = searchMatches.get(i);
            if (singleMatch.getMatchId() == matchId) {
                return singleMatch;
            }
        }

        return null;
    }
}
