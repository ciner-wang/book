package com.test.controller;

import cn.dsna.util.images.ValidateCode;
import com.test.service.AdminService;

import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping
public class PageController {
    @Autowired
    private AdminService adminService;


    /**
     * 生成验证码
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request){
        //生成验证码 ValidateCode.jar
        //四个参数：宽、高、字符数量、干扰线数量
        ValidateCode validateCode = new ValidateCode(120, 40, 5, 10);
        String code = validateCode.getCode();
        //将系统生成的验证码存放到session中
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        //将生成的验证码输出到前端页面
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String adminAcount,String adminPsw,String code,HttpServletRequest request){
        //调用登录校验的方法
        Result result = adminService.login(adminAcount, adminPsw, code,request);
        return result;
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/login_page";
    }
    @RequestMapping("/login_page")
    public String login(){return "login";}
    /**
     * 首页
     */
    @RequestMapping("/main")
    public String Main(){
        return "main";
    }
    /**
     * 返回list页面
     */
    @RequestMapping("/list_page")
    public String userlistPage(){
        return "UserList";
    }

    @RequestMapping("/add_user_page")
    public String adduserPage(){
        return "adduser";
    }

    @RequestMapping("/moneylist_page")
    public String momeylistPage(){
        return "MoneyList";
    }

    @RequestMapping("/edit_money_page")
    public String addMoneyPage(){
        return "editmoney";
    }

    @RequestMapping("/collectlist_page")
    public String collectListPage(){
        return "CollectList";
    }

    @RequestMapping("/orderlist_page")
    public String OrderListPage(){
        return "OrderList";
    }


    @RequestMapping("/booklist_page")
    public String bookListPage(){
        return "BookList";
    }

    @RequestMapping("/add_book_page")
    public String addBookListPage(){
        return "addbook";
    }

    @RequestMapping("/edit_book_page")
    public String editBookListPage(){
        return "editbook";
    }

    @RequestMapping("/bookclasslist_page")
    public String bookClassListPage(){
        return "BookClassList";
    }

    @RequestMapping("/recommmendlist_page")
    public String recommendListPage(){
        return "RecomdList";
    }
}
