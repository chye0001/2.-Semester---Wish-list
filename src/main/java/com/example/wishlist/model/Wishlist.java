package com.example.wishlist.model;

import java.util.List;

public class Wishlist {

    private int wishlistId;
    private String name;
    private String picture;
    private List<Wish> wishes;


    public Wishlist(int wishlistId, String name, String picture, List<Wish> wishes) {
        this.wishlistId = wishlistId;
        this.name = name;
        this.picture = picture;
        this.wishes = wishes;
    }


    public int getWishlistId() {
        return wishlistId;
    }
    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Wish> getWishes() {
        return wishes;
    }
    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}
