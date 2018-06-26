package com.xwtec.androidframe.ui.myOrders.bean;

import java.util.List;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */

public class OrderMultiEntity<T> {
    private int type;
    private T data;
    private List<T> dataList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
