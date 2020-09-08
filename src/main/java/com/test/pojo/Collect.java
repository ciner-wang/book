package com.test.pojo;

import java.io.Serializable;

public class Collect implements Serializable {
    private Integer collectId;

    private Integer collectNum;

    private Integer userId;

    private String bookName;

    private Double bookPrice;

    private Double collectMoney;
    private User user1;

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    private static final long serialVersionUID = 1L;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Double getCollectMoney() {
        return collectMoney;
    }

    public void setCollectMoney(Double collectMoney) {
        this.collectMoney = collectMoney;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", collectNum=" + collectNum +
                ", userId=" + userId +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", collectMoney=" + collectMoney +
                ", user1=" + user1 +
                '}';
    }
}