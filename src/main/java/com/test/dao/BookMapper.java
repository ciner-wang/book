package com.test.dao;

import com.test.pojo.Book;
import java.util.List;
import java.util.Map;

import com.test.pojo.Money;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    int deleteByPrimaryKey(@Param("bookId") Integer bookId, @Param("publishCode") Integer publishCode);

    int insert(Book record);

    Book selectByPrimaryKey(@Param("bookId") Integer bookId, @Param("publishCode") Integer publishCode);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);


    List<Book> findBookListByPage(@Param("start") Integer start, @Param("limit") Integer limit);
    Book selectByID(@Param("bookId") Integer bookId);
    Integer selectCount();

    void deleteBatch(@Param("ids") Integer[] ids);
    void searchClassBook(@Param("ids") String[] ids);
    /**
     * 查询
     */
    List<Book> searchBook(Map<String,Object> paraMaps);
    Integer searchCount(Map<String,Object> paraMaps);
}