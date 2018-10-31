package com.swc.booksearch2.model;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("title")
    private String title;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("isbn13")
    private String isbn13;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private String image;

    @SerializedName("url")
    private String url;

    /**
     * "title": "Practical MongoDB",
     "subtitle": "Architecting, Developing, and Administering MongoDB",
     "isbn13": "9781484206485",
     "price": "$32.04",
     "image": "https://itbook.store/img/books/9781484206485.png",
     "url": "https://itbook.store/books/9781484206485"
     */


    public Book(String title, String subtitle, String isbn13, String price, String image, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.isbn13 = isbn13;
        this.price = price;
        this.image = image;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}