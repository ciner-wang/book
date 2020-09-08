package com.test.controller;

import com.test.pojo.BookClass;
import com.test.pojo.Money;
import com.test.service.BookClassService;
import com.test.service.MoneyService;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/BookClass")
public class BookClassController {
    @Autowired
    private BookClassService bookClassService;

    /**
     * 获取用户信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result BookClassList(Integer page,Integer limit){
        Result result = bookClassService.getBookClassList(page,limit);
        return result;
    }
    /**
     * 批量删除
     * ajax向后台传递数组springmvc应该如何接收呢？
     * 答案：1. 不传数组(将数组里面面的元素拼接成字符串，传给后台，后台做切割处理)
     *       2. 定义实体类，实体类中的一个属性设置为数组类型，
     *       并且属性名跟传递数组参数的key一致，直接用对象接收
     *       3. ajax设置属性traditional:true
     */
//    @RequestMapping("/deleteBatch")
//    @ResponseBody
//    public Result deleteBatch(String [] ids){
//        Result result = bookClassService.deleteBatch(ids);
//        return result;
//    }

    /**
     * 添加信息
     */
    @RequestMapping("/addBookClass")
    @ResponseBody
    public Result addBookClass(BookClass bookClass){
//        if (money.getUserId()==null) {
            Result result = bookClassService.addBookClass(bookClass);
            return result;
//        }
//        else {
//            Result result = moneyService.updateById(money);
//            return result;
//        }

    }

    /**
     * 更新
     * @param
     * @return
     */
    @RequestMapping("/updateById")
    @ResponseBody
    public Result updateById(String vid,String value,String field){
        Result result = bookClassService.updateById(vid,value,field);
        System.out.println(result.getItem()+"    ");
        return result;
    }


}
