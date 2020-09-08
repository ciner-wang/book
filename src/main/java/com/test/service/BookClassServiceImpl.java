package com.test.service;

import com.test.dao.BookClassMapper;
import com.test.dao.BookMapper;
import com.test.dao.MoneyMapper;
import com.test.pojo.BookClass;
import com.test.pojo.Money;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional//做事务回滚
public class BookClassServiceImpl implements BookClassService {
    @Autowired
    private BookClassMapper bookClassMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Result getBookClassList(Integer page, Integer limit) {
        Integer start = (page-1)*limit;
        List<BookClass> monies =  bookClassMapper.findBookClassListByPage(start,limit);
       System.out.println( monies.get(1).toString()+"======");
        Result result = new Result();
        result.setItem(monies);
        Integer count  = bookClassMapper.selectCount();
        result.setTotal(count);
        return result;
    }

    @Override
    public Result deleteBatch(String[] ids) {

        Result result = new Result();
        try{
            bookClassMapper.deleteBatch(ids);
            bookMapper.searchClassBook(ids);
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
    public Result addBookClass(BookClass bookClass) {
        return null;
    }



    @Override
    public Result updateById(String vid, String value, String field) {
        Result result = new Result();
        try{
            bookClassMapper.updateById(vid,value,field);
            result.setStatus(200);
            result.setMessage("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，更新失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;

    }

    @Override
    public List<BookClass> selectAll() {
        List<BookClass> bookClasses = bookClassMapper.selectAll();

        return bookClasses;
    }

}

