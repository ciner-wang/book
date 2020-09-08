package com.test.service;

import com.test.pojo.Order;

import com.test.util.Result;


public interface OrderService {
    /**
     * 分页获取列表方法
     */
    Result getOrderList(Integer page, Integer limit);

    /**
     * 批量删除
     * @param ids:被删除的id数组
     * @return
     */
    Result deleteBatch(Integer[] ids);


    /**
     * 添加
     * @param order
     * @return
     */
    Result addOrder(Order order);
    /**
     * 编辑
     */
    Result editOrder(Integer id);
    /**
     * 更新
     */
    Result update(Order order) ;
    /**
     * 查询
     */

    Result search(String timerange,String keyword,Integer page,Integer limit);
}
