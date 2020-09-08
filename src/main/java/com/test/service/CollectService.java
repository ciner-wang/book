package com.test.service;

import com.test.pojo.Collect;
import com.test.pojo.Order;
import com.test.util.Result;

public interface CollectService {
    /**
     * 分页获取列表方法
     */
    Result getCollectList(Integer page, Integer limit);

    /**
     * 批量删除
     * @param ids:被删除的id数组
     * @return
     */
    Result deleteBatch(Integer[] ids);


    /**
     * 添加
     * @param collect
     * @return
     */
    Result addCollect(Collect collect);
    /**
     * 编辑
     */
    Result editCollect(Integer id);
    /**
     * 更新
     */
    Result update(Collect collect) ;
    /**
     * 查询
     */

    Result search(String keyword,Integer page,Integer limit);
}
