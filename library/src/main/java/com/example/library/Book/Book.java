package com.example.library.Book;

public class Book {
    private String title;        //书名
    private String author;       //作者
    private String ISBN;         //ISBN编码  
    private String publishDate;  //出版日期
    private String language;     //语言       

    public Book(String title, String author, String iSBN, String publishDate, String language) {
        this.title = title;
        this.author = author;
        this.ISBN = iSBN;
        this.publishDate = publishDate;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book [ISBN=" + ISBN + ", author=" + author + ", language=" + language + ", publishDate=" + publishDate
                + ", title=" + title + "]";
    }

}