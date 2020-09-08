package com.test.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtil {

    /**
     * 上传文件的方法
     */
    public static String uploadFile(MultipartFile upload, String parentName, HttpServletRequest request){
        //1. 获取文件的原始名称
        String originalFilename = upload.getOriginalFilename();
        //2. 文件在服务器上的存储位置(获取项目在服务器上的路径)
        // 项目路径/WEB-INF/
        String basePath = request.getServletContext().getRealPath("/WEB-INF/");
        //3. 创建存储文件的父文件夹(video或者img)
        //   右斜杠  转义字符，所以一般在 表示路径分隔时使用 \\  或者 /
        String parentPath = parentName+"/"; //  video/  或者  img/
        //4. 判断parentPath存在嘛，不存在就创建，存在直接使用
        // 项目路径/WEB-INF/video/
        File parentFile = new File(basePath + parentPath);
        if(!parentFile.exists()){
            //创建文件夹
            parentFile.mkdirs();
        }
        String fileName="";
        //5. 上传文件
        try {
            //   项目路径/WEB-INF/video/文件的名字
            fileName = getFileName(originalFilename);
            upload.transferTo(new File(basePath+parentPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parentPath+fileName;//只返回相对路径
    }
    /**
     * 封装一个获取文件名字的方法(带后缀)
     * giao.gif
     */
    public static String getFileName(String originalFilename){
        //1. 获取文件名称中最后一个  . 的位置
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //从最后一个 . 截取到文件末尾  获取文件后缀
        String suffix = originalFilename.substring(lastIndexOf);
        //2. 文件名称
        Date date = new Date();
        //日期转换格式类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String formtDate = sdf.format(date);

        return formtDate+suffix;
    }
}
