package com.test.service;

import com.test.dao.CollectMapper;
import com.test.dao.MoneyMapper;
import com.test.dao.OrderMapper;
import com.test.dao.UserMapper;
import com.test.pojo.Collect;
import com.test.pojo.Money;
import com.test.pojo.User;
import com.test.util.FileUploadUtil;
import com.test.util.Md5Util;
import com.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional//做事务回滚
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MoneyMapper moneyMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result getUserList(Integer page, Integer limit) {
        Integer start = (page-1)*limit;
        Result result = new Result();
        List<User> users = userMapper.findUserListByPage(start,limit);
        result.setItem(users);
        Integer count = userMapper.selectCount();
        result.setTotal(count);
        return result;
    }

    @Override
    public Result deleteBatch(Integer[] ids) {

        Result result = new Result();
        try{
            userMapper.deleteBatch(ids);
            moneyMapper.deleteBatch(ids);
            collectMapper.deleteBatch(ids);
            orderMapper.deleteBatch(ids);
            result.setStatus(200);//自己编的响应码 200就代表成功
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);//自己编的响应码，代表错误
            result.setMessage("网络延迟，删除失败");
        }

        return result;

    }

    @Override
    public Result uploadFile(MultipartFile uploadFile, String parentName, HttpServletRequest request) {

        Result result = new Result();
        try{
            String filePath = FileUploadUtil.uploadFile(uploadFile, parentName, request);
            result.setStatus(200);
            result.setItem(filePath);//返回文件相对路径
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public Result addUser(User user) {
        Result result = new Result();
        try{
            userMapper.insert(user);
            Money money = new Money();
            money.setUserId(user.getUserId());
            moneyMapper.insert(money);
            result.setStatus(200);
            result.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，添加失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Override
    public Result editUser(Integer id) {
        Result result = new Result();
        User user = userMapper.selectByPrimaryKey(id);
        result.setItem(user);
        return result;
    }

    @Override
    public Result update(User user) {
        Result result = new Result();

        try{
            userMapper.updateByPrimaryKey(user);
            result.setStatus(200);
            result.setMessage("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，更新失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }
    @Override
    public Result removeFile(String removeTarget, HttpServletRequest request) {
        Result result = new Result();
        //获取项目路径
        String basePath = request.getServletContext().getRealPath("/WEB-INF/");
        //项目路径/WEB-INF/video/20200611111743026.mp4
        File removeFile = new File(basePath + removeTarget);
        try{
            removeFile.delete();//删除文件
            result.setStatus(200);
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("删除失败");
        }
        return result;
    }

    @Override
    public Result search(String keyword, Integer page, Integer limit) {
        Result result = new Result();
        Map<String,Object> paraMaps = new HashMap<>();
        //计算分页查询起始位置
        Integer start = (page-1)*limit;
        paraMaps.put("start",start);
        paraMaps.put("limit",limit);
        paraMaps.put("keyword",keyword);
        //根据时间范围和关键字进行分页查询
        List<User> monies = new ArrayList<>();
        monies = userMapper.searchUser(paraMaps);
        result.setItem(monies);
        //搜索长度,便于搜索之后分页
        Integer count = userMapper.searchCount(paraMaps);

        result.setTotal(count);
        return result;
    }

    @Override
    public Result login(String userName, String userPsw,String code,HttpServletRequest request) {
        Result result = new Result();
        //从session中获取验证码
        HttpSession session = request.getSession();
        String codeValue = (String) session.getAttribute("code");
        //先比较验证码(忽略大小写比较)
        if (!code.equalsIgnoreCase(codeValue)) {
            //给出验证码输入错误的提示
            result.setStatus(500);
            result.setMessage("验证码错误，请重新输入");
        } else {
            //比较账号和密码
            //根据账号查询用户
            User user = userMapper.getUserByName(userName);
            if (user == null || !user.getUserPsw().equals(Md5Util.secretPass(userName, userPsw))) {
                result.setMessage("账号或者密码输入错误");
                result.setStatus(500);
            } else {
                //将用户信息存放到session中
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(1000);
                result.setStatus(200);
                result.setMessage("登录成功");
            }
        }
        return result;
    }
    @Override
    public User selectByID(Integer id) {
        User user= userMapper.selectByID(id);
        return user;
    }

    @Override
    public Result updateUser(User user) {
        Result result = new Result();

        try{

            userMapper.updateByPrimaryKey(user);
            result.setStatus(200);
            result.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网络延迟，添加失败l");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


}
