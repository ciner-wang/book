package com.test.pojo;

import java.io.Serializable;

public class BookClass implements Serializable {
    private Integer classId;

    private String classCode;

    private String className;

    private static final long serialVersionUID = 1L;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    @Override
    public String toString() {
        return "BookClass{" +
                "classId=" + classId +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}