package com.hjq.demo.http.model;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 统一接口数据结构
 */
public class HttpData<T> {

    /** 返回码 */
    private int code;
    /** 提示语 */
    private String msg;
    /** 数据 */
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

    public T getData() {
        return data;
    }
}