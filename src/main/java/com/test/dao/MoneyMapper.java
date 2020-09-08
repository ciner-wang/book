package com.test.dao;

import com.test.pojo.Money;
import com.test.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoneyMapper {
    int deleteByPrimaryKey(Integer umId);

    int insert(Money record);

    Money selectByPrimaryKey(Integer umId);

    List<Money> selectAll();

    int updateByPrimaryKey(Money record);
    /**
     * 搜索
     */
    List<Money> searchMoney(Map<String,Object> paraMaps);
    Integer searchCount(Map<String,Object> paraMaps);
    /**
     *
     * @param start:起始位置
     * @param limit：查询条数
     * @return
     */
    List<Money> findMoneyListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

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
     * 编辑
     */
    void updateById(@Param("vid")String vid, @Param("value")String value, @Param("field")String field);

}