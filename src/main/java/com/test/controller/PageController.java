package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping
public class PageController {
    @RequestMapping("/login")
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
    @RequestMapping("list_page")
    public String userlistPage(){
        return "UserList";
    }

    @RequestMapping("add_user_page")
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
