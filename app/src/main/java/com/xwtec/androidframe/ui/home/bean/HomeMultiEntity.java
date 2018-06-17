package com.xwtec.androidframe.ui.home.bean;

import java.util.List;

/**
 * Created by ayy on 2018/6/17.
 * Describe:xxx
 */

public class HomeMultiEntity<T> {
    private List<T> data;
    private int itemType;

    public HomeMultiEntity(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
