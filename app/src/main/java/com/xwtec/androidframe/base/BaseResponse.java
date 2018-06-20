package com.xwtec.androidframe.base;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class BaseResponse<T> {
    private String msg;
    private int code;
    private T content;
    private String status;
    private boolean success;

    public boolean isSuccess() {
        return code == 0;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
