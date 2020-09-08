package com.test.controller;

import com.test.pojo.Order;
import com.test.service.OrderService;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取用户信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result orderList(Integer page,Integer limit){
        Result result = orderService.getOrderList(page,limit);
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
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(Integer [] ids){
        Result result = orderService.deleteBatch(ids);
        return result;
    }

    /**
     * 添加信息
     */
    @RequestMapping("/addOrder")
    @ResponseBody
    public Result addOrder(Order order){
        if (order.getUserId()==null) {
            Result result = orderService.addOrder(order);
            return result;
        }
        else {
            Result result = orderService.update(order);
            return result;
        }

    }



    /**
     * 搜索
     */
    @RequestMapping("/search")
    @ResponseBody
    public Result search(String timerange,String keyword,Integer page,Integer limit){
        Result result = orderService.search(timerange,keyword,page,limit);
        return result;
    }
}
