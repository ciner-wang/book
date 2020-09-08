package com.test.service;

import com.test.dao.AdminMapper;
import com.test.pojo.Admin;

import com.test.util.Md5Util;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional//做事务回滚
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result login(String adminAcount, String adminPsw, String code, HttpServletRequest request) {
        Result result = new Result();
        //从session中获取验证码
        HttpSession session = request.getSession();
        String codeValue = (String) session.getAttribute("code");
        //先比较验证码(忽略大小写比较)
        if (!code.equalsIgnoreCase(codeValue)) {
            //给出验证码输入错误的提示
            result.setStatus(500);
            result.setMessage("验证码错误，请重新输入");
        } else {
            //比较账号和密码
            //根据账号查询用户
            Admin admin = adminMapper.getAdminByName(adminAcount);
            if (admin == null || !admin.getAdminPsw().equals(Md5Util.secretPass(adminAcount, adminPsw))) {
                result.setMessage("账号或者密码输入错误");
                result.setStatus(500);
            } else {
                //将用户信息存放到session中
                session.setAttribute("admin", admin);
                session.setMaxInactiveInterval(1000);
                result.setStatus(200);
                result.setMessage("登录成功");
            }
        }
        return result;
    }

}
