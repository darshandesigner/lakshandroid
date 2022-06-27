package com.ielts.lakshacademy.Model;

public class BookData {
    String bookName,bookUrl,createdDate;

    public BookData(String bookName, String bookUrl, String createdDate) {
        this.bookName = bookName;
        this.bookUrl = bookUrl;
        this.createdDate = createdDate;
    }

    public BookData() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
