package com.test.service;

import com.test.pojo.BookClass;
import com.test.pojo.Money;
import com.test.util.Result;

import java.util.List;

public interface BookClassService {
    /**
     * 分页获取列表方法
     */
    Result getBookClassList(Integer page, Integer limit);

    /**
     * 批量删除
     * @param ids:被删除的id数组
     * @return
     */
    Result deleteBatch(String[] ids);


    /**
     * 添加
     * @param bookClass
     * @return
     */
    Result addBookClass(BookClass bookClass);

    /**
     * 更新
     */
    Result updateById(String vid, String value, String field);
    List<BookClass> selectAll();
}
