package com.test.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {

    /**
     * 封装一个加盐加密码的方法  123456
     * 用户输入的是明文密码   进入数据库的是密文
     * 加密：不可逆
     * 这里我们使用账号作为盐，进行加密
     */
    public static String secretPass(String adminAcount,String adminPsw){
        Md5Hash md5Hash = new Md5Hash(adminAcount, adminPsw, 100);
        String passStr = md5Hash.toString();
        return passStr;
    }

    public static void main(String[] args) {
        String secretPass = secretPass("admin", "123");
        System.out.println(secretPass);
    }
}
