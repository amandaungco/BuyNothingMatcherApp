package com.example.amandaungco.buynothingmatcher.model;

public class Card {

    private int requestId;
    private String title;
    private Boolean isOffer;

    private String profileImageUrl;


    public Card (int requestId, String title, Boolean isOffer){
        this.requestId = requestId;
        this.title = title;
        this.isOffer = isOffer;
//
    }

    public Card (int requestId, String title, Boolean isOffer, String profileImageUrl){
        this.requestId = requestId;
        this.title = title;
        this.isOffer = isOffer;
        this.profileImageUrl = profileImageUrl;
    }


    public Boolean getOffer() {
        return isOffer;
    }

    public void setOffer(Boolean offer) {
        isOffer = offer;
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

    public String getProfileImageUrl(){
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }
}
