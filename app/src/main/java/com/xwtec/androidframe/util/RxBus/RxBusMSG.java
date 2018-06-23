package com.xwtec.androidframe.util.RxBus;

/**
 * Created by zhujie on 2018/3/24
 */
public class RxBusMSG {


    String code;
    Object data;

    public RxBusMSG(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
