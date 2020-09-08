package com.test.service;

import com.test.dao.MoneyMapper;
import com.test.pojo.Money;
import com.test.pojo.User;
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
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public Result getMoneyList(Integer page, Integer limit) {
        Integer start = (page-1)*limit;
        List<Money> monies =  moneyMapper.findMoneyListByPage(start,limit);
       System.out.println( monies.get(1).toString()+"======");
        Result result = new Result();
        result.setItem(monies);
        Integer count  = moneyMapper.selectCount();
        result.setTotal(count);
        return result;
    }

    @Override
    public Result deleteBatch(Integer[] ids) {
        return null;
    }

    @Override
    public Result addMoney(Money money) {
        return null;
    }



    @Override
    public Result updateById(String vid, String value, String field) {
        Result result = new Result();
        try{
            moneyMapper.updateById(vid,value,field);
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
    public Result search(String mTop, String mBase, String keyword, Integer page, Integer limit) {
        Result result = new Result();
        Map<String,Object> paraMaps = new HashMap<>();
        //计算分页查询起始位置
        Integer start = (page-1)*limit;
        paraMaps.put("start",start);
        paraMaps.put("limit",limit);
        paraMaps.put("keyword",keyword);
        paraMaps.put("mBase",mBase);
        paraMaps.put("mTop",mTop);
        //根据时间范围和关键字进行分页查询
        List<Money> monies = new ArrayList<>();
        monies = moneyMapper.searchMoney(paraMaps);
        result.setItem(monies);
        //搜索视频的长度,便于搜索之后分页
        Integer count = moneyMapper.searchCount(paraMaps);
        result.setTotal(count);
        return result;
    }
}

