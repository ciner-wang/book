package com.test.service;

import com.test.dao.OrderMapper;

import com.test.pojo.Collect;
import com.test.pojo.Order;

import com.test.pojo.User;
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
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result getOrderList(Integer page, Integer limit) {
        Integer start = (page-1)*limit;
        List<Order> monies = orderMapper.findOrderListByPage(start,limit);
        System.out.println( monies.get(1).toString()+"======");
        Result result = new Result();
        result.setItem(monies);
        Integer count  = orderMapper.selectCount();
        result.setTotal(count);
        return result;
    }

    @Override
    public Result deleteBatch(Integer[] ids) {

        return null;

    }



    @Override
    public Result addOrder(Order order) {

        return null;
    }

    @Override
    public Result editOrder(Integer id) {

        return null;
    }

    @Override
    public Result update(Order user) {

        return null;
    }

    @Override
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
        List<Order> searchVideos = orderMapper.searchOrder(paraMaps);
        result.setItem(searchVideos);
        ////搜索视频的长度,便于搜索之后分页
        Integer count = orderMapper.searchCount(paraMaps);
        result.setTotal(count);
        return result;
    }
}
