package com.example.amandaungco.buynothingmatcher.model;

public class Card {

    private int itemId;
    private String title;
    private Boolean isOffer;
    private String category;
    private int quantity;
    private String description;

    private String ImageUrl;


    public Card (int itemId, String title, Boolean isOffer, String category, int quantity, String description){
        this.itemId = itemId;
        this.title = title;
        this.isOffer = isOffer;
        this.category = category;
        this.quantity = quantity;
        this.description=description;
    }

    public Card (int itemId, String title, Boolean isOffer,String category, int quantity, String description, String ImageUrl){
        this.itemId = itemId;
        this.title = title;
        this.isOffer = isOffer;
        this.category = category;
        this.quantity = quantity;
        this.description=description;
        this.ImageUrl = ImageUrl;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOffer() {
        return isOffer;
    }

    public void setOffer(Boolean offer) {
        isOffer = offer;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl(){
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.ImageUrl = imageUrl;
    }
}
