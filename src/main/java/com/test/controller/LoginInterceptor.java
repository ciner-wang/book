package com.test.controller;


import com.test.pojo.Admin;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     * preHandle在controller调用之前执行，如果返回结果为false,那么不会继续后台controller
     * 如果返回true,正常执行controller的方法,即为不拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的路径(不包含项目根路径的)
        String uri = request.getRequestURI();
        //不拦截登录相关的请求
        //字符串的indexOf：判断uri中是否存在/login,如果存在，返回具体下标，如果不存在，返回-1
        if(uri.indexOf("/login")>=0 || uri.indexOf("/css")>=0 ||
                uri.indexOf("/img")>=0 || uri.indexOf("/js")>=0 ||
                uri.indexOf("/layui")>=0 || uri.indexOf("/getCode")>=0){
            return true;
        }
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        //如果user不为空，代表登录过了，继续执行，user为空,还没登录，进行拦截
        if(admin!=null){
            return true;
        }
        //不符合登录条件，跳转到登录页面
        request.getRequestDispatcher("/login_page").forward(request,response);
        return false;
    }

    /**
     * postHandle在controller调用之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
