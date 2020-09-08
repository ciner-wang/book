package com.test.service;

import com.test.util.Result;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    /**
     * 登录校验
     */
    Result login(String adminAcount,String adminPsw,String code,HttpServletRequest request);
}
