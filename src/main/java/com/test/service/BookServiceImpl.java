package com.test.service;

import com.test.dao.BookMapper;
import com.test.pojo.Book;
import com.test.pojo.Order;
import com.test.util.FileUploadUtil;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Result getBookList(Integer page, Integer limit) {
        //计算分页查询的起始位置
        Integer start = (page-1)*limit;
        Result result = new Result();
        //分页查询视频列表
        List<Book> books = bookMapper.findBookListByPage(start,limit);
        result.setItem(books);//获取数据
        Integer count = bookMapper.selectCount();
        result.setTotal(count);//数据长度
        return result;
    }

    @Override
    public Result deleteBatch(Integer[] ids) {
        Result result = new Result();
        try{
            bookMapper.deleteBatch(ids);
            result.setStatus(200);//自己编的响应码 200就代表成功
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);//自己编的响应码，代表错误
            result.setMessage("网络延迟，删除失败");
        }

        return result;

    }

    @Override
    public Result uploadFile(MultipartFile uploadFile, String parentName, HttpServletRequest request) {
        Result result = new Result();
        try{
            String filePath = FileUploadUtil.uploadFile(uploadFile, parentName, request);
            result.setStatus(200);
            result.setItem(filePath);//返回文件相对路径
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public Result removeFile(String removeTarget, HttpServletRequest request) {
        Result result = new Result();
        //获取项目路径
        String basePath = request.getSession().getServletContext().getRealPath("/WEB-INF/");
        //项目路径/WEB-INF/video/20200611111743026.mp4
        File removeFile = new File(basePath + removeTarget);
        try{
            removeFile.delete();//删除文件
            result.setStatus(200);
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("删除失败");
        }

        return result;

    }

    @Override
    public Result addBook(Book book) {
        Result result = new Result();
        book.toString();
        try{
            bookMapper.insert(book);
            result.setStatus(200);
            result.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，添加失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;

    }



    public Result search(String timerange,String keyword,Integer page,Integer limit) {
        Result result = new Result();
        //使用map存储传递的五个参数
        Map<String,Object> paraMaps = new HashMap<>();
        //计算分页查询起始位置
        Integer start = (page-1)*limit;
        paraMaps.put("start",start);
        paraMaps.put("limit",limit);
        paraMaps.put("keyword",keyword);
        //判断时间范围是否为空字符串
        if(!timerange.equals("")){
            //根据开始和结束时间查询
            //2020-6-15~2020-6-17   使用split进行切割，返回数组
            String [] timeArray = timerange.split("~");
            //trim():去除字符串前后的空格
            paraMaps.put("startTime",timeArray[0].trim());
            paraMaps.put("endTime",timeArray[1].trim());
        }else{
            paraMaps.put("startTime",null);
            paraMaps.put("endTime",null);
        }
        //根据时间范围和关键字进行分页查询
        List<Book> searchVideos = bookMapper.searchBook(paraMaps);
        result.setItem(searchVideos);
        ////搜索视频的长度,便于搜索之后分页
        Integer count = bookMapper.searchCount(paraMaps);
        result.setTotal(count);
        return result;
    }
    @Override
    public Book selectByID(Integer bookId) {
        Book  book= bookMapper.selectByID(bookId);
        return book;
    }

    @Override
    public Result updateBook(Book book) {
        Result result = new Result();

        try{

            bookMapper.updateByPrimaryKey(book);
            result.setStatus(200);
            result.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，添加失败l");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;

    }

}
