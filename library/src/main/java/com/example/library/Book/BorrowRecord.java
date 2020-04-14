package com.example.library.Book;

public class BorrowRecord {
    private String ISBN;        //书籍编码
    private String borrower;    //借书人
    private String borrowDate;  //借用日期-开始
    private String returnDate;  //借用日期-结束
    private Boolean isReturned; //是否已归还

    public BorrowRecord(String ISBN, String borrower, String borrowDate, String returnDate, Boolean isReturned) {
        this.ISBN = ISBN;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Override
    public String toString() {
        return "BorrowRecord [ISBN=" + ISBN + ", borrowDate=" + borrowDate + ", borrower=" + borrower + ", isReturned="
                + isReturned + ", returnDate=" + returnDate + "]";
    }

}