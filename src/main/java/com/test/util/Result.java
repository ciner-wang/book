package com.test.util;

/**
 * 返回给前端的对象
 */
public class Result {
    /**
     * 接口状态（表格的状态值默认要求是0）
     */
    private Integer status = 0;
    /**
     * 文本信息
     */
    private String message = "success";
    /**
     * 数据长度
     */
    private Integer total;
    /**
     * 返回给前端的数据(可能是单一对象，集合等等)
     */
    private Object item;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
