package com.test.service;


import com.test.dao.RecommendMapper;

import com.test.pojo.Recommend;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.List;

@Service
@Transactional//做事务回滚
public class RecmdServiceImpl implements RecmdService {

    @Autowired
    private RecommendMapper recommendMapper;
    @Override
    public Result getRecmdList(Integer page, Integer limit) {

        Integer start = (page-1)*limit;
        List<Recommend> monies =  recommendMapper.findRecmdListByPage(start,limit);
        System.out.println( monies.get(1).toString()+"======");
        Result result = new Result();
        result.setItem(monies);
        Integer count  = recommendMapper.selectCount();
        result.setTotal(count);
        return result;
    }

    @Override
    public Result deleteBatch(Integer[] ids) {
        return null;
    }

    @Override
    public Result addRecmd(Recommend recommend) {
        return null;
    }

    @Override
    public Result updateById(String vid, String value, String field) {
        Result result = new Result();
        try {
            recommendMapper.updateById(vid, value, field);
            result.setStatus(200);
            result.setMessage("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，更新失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }
}
