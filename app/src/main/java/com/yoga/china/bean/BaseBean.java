package com.yoga.china.bean;

/**
 * Created by sunsiyuan on 16/2/29.
 */
public class BaseBean<T> {

    public int code ;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
