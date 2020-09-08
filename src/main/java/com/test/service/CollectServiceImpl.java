package com.test.service;

import com.test.dao.CollectMapper;
import com.test.pojo.Collect;
import com.test.pojo.Money;
import com.test.pojo.Order;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional//做事务回滚
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Result getCollectList(Integer page, Integer limit) {
        Integer start = (page-1)*limit;
        List<Collect> monies = collectMapper.findCollectListByPage(start,limit);
        System.out.println( monies.get(1).toString()+"======");
        Result result = new Result();
        result.setItem(monies);
        Integer count  = collectMapper.selectCount();
        result.setTotal(count);
        return result;
    }

    @Override
    public Result deleteBatch(Integer[] ids) {
        return null;
    }

    @Override
    public Result addCollect(Collect collect) {
        return null;
    }

    @Override
    public Result editCollect(Integer id) {
        return null;
    }

    @Override
    public Result update(Collect collect) {
        return null;
    }

    @Override
    public Result search(String keyword, Integer page, Integer limit) {
        Result result = new Result();
        Map<String,Object> paraMaps = new HashMap<>();
        //计算分页查询起始位置
        Integer start = (page-1)*limit;
        paraMaps.put("start",start);
        paraMaps.put("limit",limit);
        paraMaps.put("keyword",keyword);
        //根据时间范围和关键字进行分页查询
       // List<Collect> monies = new ArrayList<>();
        List<Collect> monies = collectMapper.searchCollect(paraMaps);
        result.setItem(monies);
        //搜索长度,便于搜索之后分页
        Integer count = collectMapper.searchCount(paraMaps);

        result.setTotal(count);
        return result;
    }
}
