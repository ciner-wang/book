package com.test.dao;

import com.test.pojo.BookClass;
import java.util.List;

import com.test.pojo.Money;
import org.apache.ibatis.annotations.Param;

public interface BookClassMapper {
    int deleteByPrimaryKey(@Param("classId") Integer classId, @Param("classCode") String classCode);

    int insert(BookClass record);

    BookClass selectByPrimaryKey(@Param("classId") Integer classId, @Param("classCode") String classCode);

    List<BookClass> selectAll();

    int updateByPrimaryKey(BookClass record);
    /**
     *
     * @param start:起始位置
     * @param limit：查询条数
     * @return
     */
    List<BookClass> findBookClassListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 查询所有条数
     * @return
     */
    Integer selectCount();

    /**
     * 批量删除
     * @param ids:被删除的id数组
     */
    void deleteBatch(@Param("ids") String[] ids);

    /**
     * 编辑
     */
    void updateById(@Param("vid")String vid, @Param("value")String value, @Param("field")String field);
}