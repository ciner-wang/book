package com.test.service;

import com.test.pojo.Money;

import com.test.util.Result;

public interface MoneyService {
    /**
     * 分页获取列表方法
     */
    Result getMoneyList(Integer page, Integer limit);

    /**
     * 批量删除
     * @param ids:被删除的id数组
     * @return
     */
    Result deleteBatch(Integer[] ids);


    /**
     * 添加
     * @param money
     * @return
     */
    Result addMoney(Money money);

    /**
     * 更新
     */
    Result updateById(String vid, String value, String field);
    /**
     * 查询
     */

    Result search(String mTop,String mBase,String keyword,Integer page,Integer limit);
}
