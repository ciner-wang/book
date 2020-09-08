package com.test.controller;

import com.test.pojo.Collect;
import com.test.pojo.User;
import com.test.service.CollectService;
import com.test.service.UserService;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 获取用户信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result collectList(Integer page,Integer limit){
        Result result = collectService.getCollectList(page,limit);
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
        Result result = collectService.deleteBatch(ids);
        return result;
    }

    /**
     * 添加信息
     */
    @RequestMapping("/addCollect")
    @ResponseBody
    public Result addCollect(Collect collect){
        if (collect.getUserId()==null) {
            Result result = collectService.addCollect(collect);
            return result;
        }
        else {
            Result result = collectService.update(collect);
            return result;
        }

    }

    /**
     * 编辑
     * @param ids
     * @return
     */
    @RequestMapping("/editCollect")
    @ResponseBody
    public Result editCollect(Integer ids){
       Result result= collectService.editCollect(ids);
        return result;
    }
    /**
     * 搜索
     */
    @RequestMapping("/search")
    @ResponseBody
    public Result search(String keyword,Integer page,Integer limit){
        Result result = collectService.search(keyword,page,limit);
        return result;
    }

}
