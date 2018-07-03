package com.xwtec.androidframe.ui.express;

/**
 * Created by ayy on 2018/7/1.
 * Describe:xxx
 */

public class Express {

    /**
     * id : 1
     * expressCompany : 跨越速运
     * sort : 1
     * status : 0
     * createTime : 1530420930000
     * updateTime : 1530420951000
     */

    private int id;
    private String expressCompany;
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

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
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
