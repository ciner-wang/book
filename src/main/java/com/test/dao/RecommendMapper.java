package com.test.dao;

import com.test.pojo.Order;
import com.test.pojo.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer recmdId);

    int insert(Recommend record);

    Recommend selectByPrimaryKey(Integer recmdId);

    List<Recommend> selectAll();

    int updateByPrimaryKey(Recommend record);
    /**
     *
     * @param start:起始位置
     * @param limit：查询条数
     * @return
     */
    List<Recommend> findRecmdListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

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