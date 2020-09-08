package com.test.service;


import com.test.pojo.Recommend;
import com.test.util.Result;


public interface RecmdService {
    Result getRecmdList(Integer page, Integer limit);

    Result deleteBatch(Integer[] ids);


    Result addRecmd(Recommend recommend);

    Result updateById(String vid, String value, String field);

}
