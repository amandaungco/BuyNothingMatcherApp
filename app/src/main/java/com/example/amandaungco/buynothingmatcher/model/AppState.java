package com.example.amandaungco.buynothingmatcher.model;

public class AppState {
    //singleton

    final public static AppState INSTANCE = new AppState();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private AppState() {


    }
}
