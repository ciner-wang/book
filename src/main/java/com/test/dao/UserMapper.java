package com.test.dao;

import com.test.pojo.Money;
import com.test.pojo.User;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("userName") String userName);

    int insert(User record);

    User selectByPrimaryKey(@Param("userId") Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     *
     * @param start:起始位置
     * @param limit：查询条数
     * @return
     */
    List<User> findUserListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

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
     * @param paraMaps
     * @return
     */
    List<User> searchUser(Map<String,Object> paraMaps);
    Integer searchCount(Map<String,Object> paraMaps);
    /**
     * 根据账号查询用户
     * @param userName
     * userPsw
     * @return
     */
    User getUserByName(@Param("userName") String userName);
    User selectByID(@Param("id") Integer id);
    User findByName(String userName);
    void save(User user);
}