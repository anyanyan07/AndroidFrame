package com.xwtec.androidframe.ui.goodDetail.bean;

import java.util.List;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public class GoodDetailMultiEntity<T> {
    private int type;
    private List<T> dataList;
    private T data;

    public GoodDetailMultiEntity(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
