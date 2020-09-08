package com.test.dao;

import com.test.pojo.Collect;
import com.test.pojo.Money;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(Collect record);

    Collect selectByPrimaryKey(Integer collectId);

    List<Collect> selectAll();

    int updateByPrimaryKey(Collect record);
    /**
     *
     * @param start:起始位置
     * @param limit：查询条数
     * @return
     */
    List<Collect> findCollectListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

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
     * 搜索
     */
    List<Collect> searchCollect(Map<String,Object> paraMaps);
    Integer searchCount(Map<String,Object> paraMaps);
}
