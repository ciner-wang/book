package com.test.service;

import com.test.pojo.User;
import com.test.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 分页获取用户信息列表方法
     */
    Result getUserList(Integer page, Integer limit);

    /**
     * 批量删除
     * @param ids:被删除的id数组
     * @return
     */
    Result deleteBatch(Integer[] ids);
    /**
     * 上传文件
     * @param uploadFile
     * @param parentName
     * @param request
     * @return
     */
    Result uploadFile(MultipartFile uploadFile, String parentName, HttpServletRequest request);

    /**
     * 添加
     * @param user
     * @return
     */
     Result addUser(User user);
    /**
     * 编辑用户
     */
    Result editUser(Integer id);
    /**
     * 更新
     */
    Result update(User user) ;
    /**
     * 删除文件
     * @param removeTarget
     * @param request
     * @return
     */
    Result removeFile(String removeTarget, HttpServletRequest request);
    /**
     * 查询
     */

    Result search(String keyword,Integer page,Integer limit);
    /**
     * 登录校验
     */
    Result login(String useName,String userPsw,String code,HttpServletRequest request);
    User selectByID(Integer id);


    Result updateUser(User user);
}
