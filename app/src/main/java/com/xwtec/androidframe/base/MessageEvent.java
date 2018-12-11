package com.xwtec.androidframe.base;

/**
 * @Author ayy
 * @Date 2018/10/29.
 * Describe:xxx
 */

public class MessageEvent {
    private String msgCode;
    private Object data;

    public MessageEvent(String msgCode) {
        this.msgCode = msgCode;
    }

    public MessageEvent(String msgCode, Object data) {
        this.msgCode = msgCode;
        this.data = data;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
