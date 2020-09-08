package com.test.controller;


import com.test.pojo.User;
import com.test.service.BookClassService;
import com.test.service.UserService;
import com.test.util.Result;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result userList(Integer page,Integer limit){
        Result result = userService.getUserList(page,limit);
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
        Result result = userService.deleteBatch(ids);
        return result;
    }
    /**
     * 上传文件(视频或者图片的上传接口)
     * 返回值：上传文件存储的相对路径
     * uploadFile:对应layui里面的field
     * parentName：存储上传文件的父文件夹(video或者img)
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadFile(MultipartFile uploadFile, String parentName, HttpServletRequest request){

        Result result = userService.uploadFile(uploadFile,parentName,request);
        return result;
    }
    /**
     * 添加信息
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Result addUser(User user){
        if (user.getUserId()==null) {
            Result result = userService.addUser(user);
            return result;
        }
        else {
            Result result = userService.update(user);
            return result;
        }

    }

    /**
     * 编辑
     * @param ids
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Result editUser(Integer ids){
       Result result= userService.editUser(ids);
        return result;
    }
    /**
     * 删除上传文件
     * removeTarget:被删除文件的相对路径
     */
    @RequestMapping("/removeFile")
    @ResponseBody
    public Result removeFile(String removeTarget,HttpServletRequest request){
        System.out.println(removeTarget);
        Result result = userService.removeFile(removeTarget,request);
        return result;
    }
    /**
     * 搜索
     */
    @RequestMapping("/search")
    @ResponseBody
    public Result search(String keyword,Integer page,Integer limit){
        Result result = userService.search(keyword,page,limit);
        return result;
    }

    @RequestMapping("/edit")
    public String  edit( Integer id,Model model){
        User user = userService.selectByID(id);
        //页面传递对象
        model.addAttribute("user",user);
        //页面传递集合
        return "edituser";

    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser( User  user){
        System.out.println(user.toString());
        Result result = userService.updateUser(user);
        return result;
    }
}
