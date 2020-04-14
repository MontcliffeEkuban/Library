package com.example.library.Book;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    Book[] books = {
        new Book("JavaScript高级程序设计(第3版)", "尼古拉斯·泽卡斯", "9787115275790", "2012-03-29", "Chinese"),
        new Book("JavaScript DOM编程艺术(第2版)", "Jeremy Keith/Jeffrey Sambells", "9787115249999", "2011-04-02", "Chinese"),
        new Book("你不知道的JavaScript", "Kyle Simpson", "9787115385734", "2015-04-14", "Chinese"),
        new Book("深入理解ES6", "Nicholas C. Zakas", "9787121317989", "2017-07-01", "Chinese"),
        new Book("JavaScript设计模式", "Ross Harmes/Dustin Diaz", "9787115191281", "2008-06-19", "Chinese"),
        new Book("JavaScript", "Douglas Crockford", "9780596517748", "2008-05-24", "English"),
        new Book("DOM Scripting", "Jeremy Keith", "9781590595336", "2005-9-19", "English"),
        new Book("JavaScript框架设计", "司徒正美", "9787115343581", "2015-04-14", "Chinese"),
        new Book("疯狂HTML 5/CSS 3/JavaScript讲义", "李刚", "9787121168635", "2012-5-1", "Chinese"),
        new Book("JavaScript: The Definitive Guide, 5th Edition", "David Flanagan", "9780596101992", "2006-08-01", "English"),
        new Book("Object-Oriented JavaScript", "Stoyan Stefanov", "9781847194145", "2008-07-24", "English"),
        new Book("编写可维护的JavaScript", "扎卡斯", "9787115310088", "2015-04-14", "Chinese"),
        new Book("High Performance JavaScript", "尼古拉斯·泽卡斯", "9780596802790", "2010-4-2", "English"),
        new Book("JavaScript面向对象精要", "尼古拉斯·泽卡斯", "9787115383846", "2007-06-10", "Chinese"),
        new Book("学习JavaScript数据结构与算法", "Loiane Groner", "9787115404145", "2015-10-1", "Chinese"),
        new Book("Eloquent Javascript, 3rd Edition", "Marijn Haverbeke", "9781593279509", "2018-10-30", "English"),
        new Book("深入浅出Node.js", "朴灵", "9787115335500", "2013-12-01", "Chinese"),
        new Book("JavaScript模式", "Stoyan Stefanov", "9787512329232", "2012-07-09", "Chinese"),
        new Book("HTML5权威指南", "Adam Freeman", "9787115338365", "2014-01-20", "Chinese"),
        new Book("ES6标准入门", "阮一峰", "9787121324758", "2017-09-24", "Chinese"),
        new Book("基于MVC的JavaScript Web富应用开发", "Alex MacCaw", "9787121109560", "2012-05-30", "Chinese"),
        new Book("Secrets of the JavaScript Ninja", "John Resig/Bear Bibeault", "9781933988696", "2013-01-17", "English"),
        new Book("JavaScript设计模式与开发实践", "曾探", "9787115388889", "2015-05-22", "Chinese"),
        new Book("精通JavaScript", "John Resig", "9787115175403", "2008-04-01", "Chinese"),
        new Book("JavaScript异步编程", "Trevor Burnham", "9787115316578", "2013-06-16", "Chinese")
    };

    //存放全部书籍和借书记录
    ArrayList<Book> bookList = new ArrayList<>(Arrays.asList(books));
    ArrayList<BorrowRecord> borrowRecordList = new ArrayList<>();

    //查看全部书籍
    @GetMapping("")
    public ArrayList<Book> findAllBook(){
        return bookList;
    }

    //根据 ISBN 查询书籍
    @GetMapping("/isbn/{ISBN}")
    public Book findByISBN(@PathVariable String ISBN){
        Book result = null;
        for (Book book : bookList) {
            if(book.getISBN().equals(ISBN)){
                result = book;
                break;
            }
        }
        return result;
    }

    //根据 author 查询书籍
    @GetMapping("/author/{author}")
    public ArrayList<Book> findBookByAuthor(@PathVariable String author){
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : bookList) {
            if(book.getAuthor().equals(author)){
                result.add(book);
            }
        }

        return result;
    }

    //产看全部借书记录
    @GetMapping("/record")
    public ArrayList<BorrowRecord> findAllRecord(){
        return borrowRecordList;
    }

    //根据 ISBN 产看借书记录
    @GetMapping("/record/isbn/{ISBN}")
    public ArrayList<BorrowRecord> findRecordByISBN(@PathVariable String ISBN){
        ArrayList<BorrowRecord> result = new ArrayList<>();
        for (BorrowRecord record : borrowRecordList) {
            if(record.getISBN().equals(ISBN)){
                result.add(record);
            }
        }
        return borrowRecordList;
    }

    //根据借书人产看借书记录
    @GetMapping("/record/borrower/{borrower}")
    public ArrayList<BorrowRecord> findRecordByBorrower(@PathVariable String borrower){
        ArrayList<BorrowRecord> result = new ArrayList<>();
        for (BorrowRecord record : result) {
            if(record.getBorrower().equals(borrower)){
                result.add(record);
            }
        }
        return result;
    }

    //根据时间查询借书记录(YYYY-MM-DD)
    @GetMapping("/record/borrow/{borrowDate}/return/{returnDate}")
    public ArrayList<BorrowRecord> findRecordByTime(@PathVariable String borrowDate, @PathVariable String returnDate){

        //将请求的日期转化为 int 类型，方便比较大小
        int borrowDateToInt = Integer.parseInt(borrowDate.replaceAll("-", ""));
        int returnDateToInt = Integer.parseInt(returnDate.replaceAll("-", ""));

        //遍历借书记录
        ArrayList<BorrowRecord> result = new ArrayList<>();
        for (BorrowRecord record : borrowRecordList) {
            //将借书记录里的借阅日期和归还日期转换为 int 类型
            int from = Integer.parseInt(record.getBorrowDate().replaceAll("-", ""));
            int to = Integer.parseInt(record.getReturnDate().replaceAll("-", ""));

            //如果记录里的日期区间在请求的日期区间里，将其添加至查询结果中
            if(from < returnDateToInt || to > borrowDateToInt){
                result.add(record);
            }
        }

        return result;
    }

    //借书
    @PostMapping("/{ISBN}/borrow")
    public String borrowBook(@PathVariable String ISBN, @RequestBody BorrowRecord borrowRequest){
        Boolean bookExist = false;
        Boolean isBorrowed = false;
        //遍历bookList查看书籍是否存在
        for (Book book : bookList) {
            if(book.getISBN().equals(ISBN)){
            bookExist = true;  
            break;  
            }
        }  
        
        if(bookExist == false){
            //返回书籍不存在的消息，借阅失败
            return "book not exist";
        } else {
            //遍历借书记录看书是否被借出
            for (BorrowRecord borrowRecord : borrowRecordList){
                if(borrowRecord.getISBN().equals(ISBN) && !borrowRecord.getIsReturned()){
                    isBorrowed = true;
                    break;
                }
            }
 
            if(isBorrowed){
                //返回书籍已借出的消息，借阅失败
                return "book is already borrowed";
            } else { 
                //如果未借出，则成功借阅
                borrowRequest.setISBN(ISBN);
                borrowRequest.setIsReturned(false);
                borrowRecordList.add(borrowRequest);
                return "successfully borrowed";
            }
        }
    }

    //还书
    @PostMapping("/{ISBN}/return/{username}")
    public String returnBook(@PathVariable String ISBN, @PathVariable String username){
         Boolean bookExist = false;
         Boolean successReturned = false;
        //遍历bookList查看书籍是否存在
        for (Book book : bookList) {
            if(book.getISBN().equals(ISBN)){
            bookExist = true;  
            break;  
            }
        } 

        if(!bookExist){
            //返回书籍不存在的消息，归还失败
            return "book not exist";
        } else{
            //遍历借书记录，找到对应的记录并归还
            for (BorrowRecord record : borrowRecordList) {
                if(record.getISBN().equals(ISBN) && record.getBorrower().equals(username)){
                    successReturned = true;
                    record.setIsReturned(true);
                    break;
                }
            }
            return successReturned ? "successfully returned" : "wrong information";
        }
    }
}