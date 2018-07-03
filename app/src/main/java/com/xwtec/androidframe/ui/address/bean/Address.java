package com.xwtec.androidframe.ui.address.bean;

import java.io.Serializable;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public class Address implements Serializable{


    /**
     * id : 1
     * receiver : harryWang
     * receiveArea : 南京江宁区
     * detailsAddress : 南京江宁区软件大道
     * phone : 13813858204
     * isDefault : 1
     * status : 0
     * createTime : 1529549068000
     * updateTime : 1529549070000
     */

    private int id;
    private String receiver;
    private String receiveArea;
    private String detailsAddress;
    private String phone;
    private int isDefault;
    private int status;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveArea() {
        return receiveArea;
    }

    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
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
