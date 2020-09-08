package com.test.pojo;

import java.io.Serializable;

public class Recommend implements Serializable {
    private Integer recmdId;

    private String recmdClass;

    private Integer publishCode;

    private String recmdPeople;
    private String bookName;

    private static final long serialVersionUID = 1L;

    public Integer getRecmdId() {
        return recmdId;
    }

    public void setRecmdId(Integer recmdId) {
        this.recmdId = recmdId;
    }

    public String getRecmdClass() {
        return recmdClass;
    }

    public void setRecmdClass(String recmdClass) {
        this.recmdClass = recmdClass == null ? null : recmdClass.trim();
    }

    public Integer getPublishCode() {
        return publishCode;
    }

    public void setPublishCode(Integer publishCode) {
        this.publishCode = publishCode;
    }


    public String getRecmdPeople() {
        return recmdPeople;
    }

    public void setRecmdPeople(String recmdPeople) {
        this.recmdPeople = recmdPeople;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}