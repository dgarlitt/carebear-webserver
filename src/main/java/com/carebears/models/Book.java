package com.carebears.models;

public class Book {

    public final String title;
    public final double price;
    public final boolean available;

    public Book(String title, double price, boolean available) {
        this.title = title;
        this.price = price;
        this.available = available;
    }

}
