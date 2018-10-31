package com.swc.booksearch2.model;

import com.google.gson.annotations.SerializedName;
import com.swc.booksearch2.model.Book;

import java.util.List;

public class RawResponse {

    @SerializedName("error")
    private String error;

    @SerializedName("total")
    private String total;

    @SerializedName("page")
    private String page;

    @SerializedName("books")
    private List<Book> books;

    /**
     * "title": "Practical MongoDB",
     "subtitle": "Architecting, Developing, and Administering MongoDB",
     "isbn13": "9781484206485",
     "price": "$32.04",
     "image": "https://itbook.store/img/books/9781484206485.png",
     "url": "https://itbook.store/books/9781484206485"
     */


    public RawResponse(String error, String total, String page, List<Book> books) {
        this.error = error;
        this.total = total;
        this.page = page;
        this.books = books;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}