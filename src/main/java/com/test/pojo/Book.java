package com.test.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book implements Serializable {
    private Integer bookId;

    private Integer publishCode;

    private String bookImage;

    private String bookName;

    private String bookAuthor;

    private String publishName;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date publishTime;

    private Double bookPrice;

    private Double salesNum;

    private String classCode;

    private String bookInfo;


    private BookClass bookClass;

    public BookClass getBookClass() {
        return bookClass;
    }

    public void setBookClass(BookClass bookClass) {
        this.bookClass = bookClass;
    }

    private static final long serialVersionUID = 1L;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPublishCode() {
        return publishCode;
    }

    public void setPublishCode(Integer publishCode) {
        this.publishCode = publishCode;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage == null ? null : bookImage.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName == null ? null : publishName.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Double getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Double salesNum) {
        this.salesNum = salesNum;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo == null ? null : bookInfo.trim();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", publishCode=" + publishCode +
                ", bookImage='" + bookImage + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publishName='" + publishName + '\'' +
                ", publishTime=" + publishTime +
                ", bookPrice=" + bookPrice +
                ", salesNum=" + salesNum +
                ", classCode='" + classCode + '\'' +
                ", bookInfo='" + bookInfo + '\'' +
                ", bookClass=" + bookClass +
                '}';
    }
}