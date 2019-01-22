package com.example.amandaungco.buynothingmatcher.model;

public class Card {

    private int requestId;
    private String title;

//    private String profileImageUrl;

    public Card (int requestId, String title){
        this.requestId = requestId;
        this.title = title;
//        this.profileImageUrl = profileImageUrl;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
