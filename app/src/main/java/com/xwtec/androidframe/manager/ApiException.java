package com.xwtec.androidframe.manager;

/**
 * Created by ayy on 2018/7/3.
 * Describe:xxx
 */

public class ApiException extends RuntimeException {
    private int code;

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
