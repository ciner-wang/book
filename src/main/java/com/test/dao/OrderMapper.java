package com.test.dao;

import com.test.pojo.Money;
import com.test.pojo.Order;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int deleteByPrimaryKey(@Param("orderId") Integer orderId, @Param("orderCode") Integer orderCode);

    int insert(Order record);

    Order selectByPrimaryKey(@Param("orderId") Integer orderId, @Param("orderCode") Integer orderCode);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);
    /**
     *
     * @param start:起始位置
     * @param limit：查询条数
     * @return
     */
    List<Order> findOrderListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询所有条数
     * @return
     */
    Integer selectCount();

    /**
     * 批量删除
     * @param ids:被删除的id数组
     */
    void deleteBatch(@Param("ids") Integer[] ids);
    /**
     * 查询
     */
    List<Order> searchOrder(Map<String,Object> paraMaps);
    Integer searchCount(Map<String,Object> paraMaps);
}
