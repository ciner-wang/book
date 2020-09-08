package com.test.controller;

import com.test.pojo.Book;
import com.test.pojo.Recommend;
import com.test.service.BookService;
import com.test.service.RecmdService;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/Recommend")
public class RecmdController {

    @Autowired
    private RecmdService recmdService;


    /**
     * 分页查询视频列表
     */
    @RequestMapping("/list")
    @ResponseBody// json格式数据
    public Result RecommendList(Integer page,Integer limit){
        Result result = recmdService.getRecmdList(page,limit);
        result.toString();
        return result;
    }


    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(Integer [] ids){

        Result result = recmdService.deleteBatch(ids);
        return result;
    }


    /**
     * 更新
     * @param
     * @return
     */
    @RequestMapping("/updateById")
    @ResponseBody
    public Result updateById(String vid,String value,String field){
        Result result = recmdService.updateById(vid,value,field);
        System.out.println(result.getItem()+"    ");
        return result;
    }
}
