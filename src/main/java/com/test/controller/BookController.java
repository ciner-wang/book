package com.test.controller;

import com.test.pojo.Book;
import com.test.pojo.BookClass;
import com.test.service.BookClassService;
import com.test.service.BookService;
import com.test.service.CollectService;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookClassService bookClassService;

    /**
     * 分页查询视频列表
     */
    @RequestMapping("/list")
    @ResponseBody// json格式数据
    public Result collectList(Integer page,Integer limit){
        Result result = bookService.getBookList(page,limit);
        result.toString();
        return result;
    }


    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(Integer [] ids){
        for (int i:ids){
            System.out.println(i);
        }
        Result result = bookService.deleteBatch(ids);
        return result;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadFile(MultipartFile uploadFile, String parentName, HttpServletRequest request){
        Result result = bookService.uploadFile(uploadFile,parentName,request);
        return result;
    }

    @RequestMapping("/removeFile")
    @ResponseBody
    public Result removeFile(String removeTarget,HttpServletRequest request){
        Result result = bookService.removeFile(removeTarget,request);
        return result;
    }

    /**
     * 添加视频
     * springmvc:直接使用对象接收参数
     *      实体类属性根参数标签的name一致
     *      实体类有get set方法
     */
    @RequestMapping("/addBook")
    @ResponseBody
    public Result addBook( Book  book){
      Result result = bookService.addBook(book);
//        for (String key : book.keySet()) { HashMap<String,Object>
//            System.out.println("key= " + key + " and value= " + book.get(key));
//        }
        System.out.println(book.getPublishTime());
       book.toString();
       System.out.println(" hbibbuib");
        return result;
    }

    @RequestMapping("/search")
    @ResponseBody
    public Result search(String timerange,String keyword,Integer page,Integer limit){
        Result result = bookService.search(timerange,keyword,page,limit);
        return result;
    }
    @RequestMapping("/edit")
    public String  edit( Integer id,Model model){
        System.out.println(id);
        Book book = bookService.selectByID(id);
        List<BookClass> bookclass= bookClassService.selectAll();
//        System.out.println(book.getBookInfo());
        //页面传递对象
        model.addAttribute("book",book);
//        //页面传递集合
        model.addAttribute("bookclass",bookclass);
        return "editbook";

    }

    @RequestMapping("/updateBook")
    @ResponseBody
    public Result updateBook( Book  book){
        System.out.println("这是bookController");
        System.out.println(book.toString());
        Result result = bookService.updateBook(book);
        return result;
    }
}
