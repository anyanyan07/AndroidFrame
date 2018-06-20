package com.xwtec.androidframe.ui.home.bean;

/**
 * Created by ayy on 2018/6/16.
 * Describe:首页商品定义
 */

public class TabBean {

    /**
     * id : 1
     * defineName : 推荐商品
     * sort : 1
     * status : 0
     * createTime : 1529392490000
     * updateTime : 1529392492000
     */

    private int id;
    private String defineName;
    private int sort;
    private int status;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefineName() {
        return defineName;
    }

    public void setDefineName(String defineName) {
        this.defineName = defineName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
