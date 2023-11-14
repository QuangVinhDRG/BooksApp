package com.example.booksapp;

import android.graphics.Bitmap;

public class Book {
    private String title, author, publisher, description, bookImageLink;

    public Book(String title, String author, String publisher, String description, String bookImageLink) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.bookImageLink = bookImageLink;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return "Author: " + author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return "Publisher: " + publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookImageLink() {
        return bookImageLink;
    }

    public void setBookImageLink(String bookImageLink) {
        this.bookImageLink = bookImageLink;
    }
}
