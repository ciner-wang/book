package com.test.pojo;

import java.io.Serializable;

public class Money implements Serializable {
    private Integer umId;

    private Double umMoney;

    private Integer userId;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static final long serialVersionUID = 1L;

    public Integer getUmId() {
        return umId;
    }

    public void setUmId(Integer umId) {
        this.umId = umId;
    }

    public Double getUmMoney() {
        return umMoney;
    }

    public void setUmMoney(Double umMoney) {
        this.umMoney = umMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Money{" +
                "umId=" + umId +
                ", umMoney=" + umMoney +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}