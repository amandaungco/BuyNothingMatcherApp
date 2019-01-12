package com.example.amandaungco.buynothingmatcher;

public enum Category {
    FURNITURE("Furniture"),
    ELECTRONIC("Electronic"),
    CLOTHING("Clothing"),
    SHOES("Shoes"),
    OUTDOOR("Outdoor"),
    SPORTS("Sport"),
    BOOKS("Books"),
    KITCHEN("Kitchen"),
    BABY("Baby"),
    AUTOMOBILE("Automobile"),
    JEWELRY("Jewelry"),
    APPLIANCES("Appliances"),
    HEALTH("Health"),
    ARTSCRAFTS("Arts And Crafts"),
    TICKETS("Tickets and Entertainment"),
    MISC("Miscellaneous");

    private String label;

    Category(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}