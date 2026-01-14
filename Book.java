package com.library;

public class Book {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private String publishDate;
    private String genre;
    private int ageRating;
    private String borrowerID; 
    private String borrowerName; // 用于 Task 8B 显示借阅者姓名

    public Book(String id, String isbn, String title, String author, String publishDate, String genre, int ageRating) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.genre = genre;
        this.ageRating = ageRating;
        this.borrowerID = null;
        this.borrowerName = null;
    }

    // Getters
    public String getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublishDate() { return publishDate; }
    public String getGenre() { return genre; }
    public int getAgeRating() { return ageRating; }
    public String getBorrowerID() { return borrowerID; }
    public String getBorrowerName() { return borrowerName; }

    // Setters
    public void setBorrowerID(String borrowerID) { this.borrowerID = borrowerID; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
}