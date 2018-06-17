package com.xwtec.androidframe.ui.classify.bean;

/**
 * Created by ayy on 2018/6/16.
 * Describe:分类-类别
 */

public class CategoryBean {

    /**
     * id : 1
     * name : 米粮豆谷
     * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
     */

    private int id;
    private String name;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
