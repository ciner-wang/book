package com.test.pojo;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer adminId;

    private String adminAcount;

    private String adminPsw;

    private static final long serialVersionUID = 1L;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAcount() {
        return adminAcount;
    }

    public void setAdminAcount(String adminAcount) {
        this.adminAcount = adminAcount == null ? null : adminAcount.trim();
    }

    public String getAdminPsw() {
        return adminPsw;
    }

    public void setAdminPsw(String adminPsw) {
        this.adminPsw = adminPsw == null ? null : adminPsw.trim();
    }
}