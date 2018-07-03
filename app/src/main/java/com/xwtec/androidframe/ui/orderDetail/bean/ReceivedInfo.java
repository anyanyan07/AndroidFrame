package com.xwtec.androidframe.ui.orderDetail.bean;

/**
 * Created by ayy on 2018/7/1.
 * Describe:已收货订单详情
 */

public class ReceivedInfo {

    /**
     * goodsId : 1
     * orderId : 8
     * receiverId : 1
     * title : 正宗地道  东北坚果
     * introduction : 185gx2袋
     * orderNumber : 62568964512808960
     * status : 7
     * unitPrice : 36.90
     * goodsNumber : 2
     * totalPrice : 73.80
     * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
     * createTime : 1529653604000
     * updateTime : 1529738124000
     * remark : 48小时后该订单自动关闭
     * receiveAddress : {"id":1,"receiver":"龙三少","receiveArea":"南京江宁区","detailsAddress":"南京江宁区软件大道","phone":"13813858204","isDefault":0,"userId":45,"status":0,"createTime":1529549068000,"updateTime":1529561270000}
     * expressPrice : 0.00
     */

    private int goodsId;
    private int orderId;
    private int receiverId;
    private String title;
    private String introduction;
    private String orderNumber;
    private int status;
    private String unitPrice;
    private int goodsNumber;
    private String totalPrice;
    private String imgUrl;
    private long createTime;
    private long updateTime;
    private String remark;
    private ReceiveAddressBean receiveAddress;
    private String expressPrice;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ReceiveAddressBean getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(ReceiveAddressBean receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }

    public static class ReceiveAddressBean {
        /**
         * id : 1
         * receiver : 龙三少
         * receiveArea : 南京江宁区
         * detailsAddress : 南京江宁区软件大道
         * phone : 13813858204
         * isDefault : 0
         * userId : 45
         * status : 0
         * createTime : 1529549068000
         * updateTime : 1529561270000
         */

        private int id;
        private String receiver;
        private String receiveArea;
        private String detailsAddress;
        private String phone;
        private int isDefault;
        private int userId;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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
}
