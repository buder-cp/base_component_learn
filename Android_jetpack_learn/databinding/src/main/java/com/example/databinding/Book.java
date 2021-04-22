package com.example.databinding;

/**
 * 共用的Model，有些字段可能没有用到
 */
public class Book {
    public String title;
    public String author;
    public int rating;
    public String image;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}