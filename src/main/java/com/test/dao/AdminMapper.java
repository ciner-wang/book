package com.test.dao;

import com.test.pojo.Admin;
import com.test.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    /**
     * 根据账号查询用户
     * @param
     * @return
     */
    Admin getAdminByName(@Param("adminAcount") String adminAcount);




}