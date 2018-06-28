package com.xwtec.androidframe.ui.affirmOrder.bean;

import java.util.List;

/**
 * Created by ayy on 2018/6/27.
 * Describe:xxx
 */

public class AffirmResponse {


    /**
     * receiveAddress : {"id":3,"receiver":"龙少阁主","receiveArea":"南京市鼓楼区","detailsAddress":"南京市鼓楼区中山东路","phone":"13813858204","isDefault":1,"userId":45,"status":0,"createTime":1529562331000,"updateTime":1529562331000}
     * orderGoods : [{"shopCarId":1,"goodsId":1,"orderId":null,"receiverId":null,"title":"正宗地道  东北坚果","introduction":"185gx2袋","orderNumber":null,"status":null,"unitPrice":"36.90","goodsNumber":2,"totalPrice":"73.80","imgUrl":"https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg","createTime":null,"updateTime":null},{"shopCarId":2,"goodsId":2,"orderId":null,"receiverId":null,"title":"正宗地道  东北菌类","introduction":"120gx2袋","orderNumber":null,"status":null,"unitPrice":"35.6","goodsNumber":5,"totalPrice":"178.0","imgUrl":null,"createTime":null,"updateTime":null}]
     * totalPrice : 251.80
     * expressPrice : 0.00
     * totalNumber : 2
     */

    private ReceiveAddressBean receiveAddress;
    private String totalPrice;
    private String expressPrice;
    private int totalNumber;
    private List<OrderGoodsBean> orderGoods;

    public ReceiveAddressBean getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(ReceiveAddressBean receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<OrderGoodsBean> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoodsBean> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public static class ReceiveAddressBean {
        /**
         * id : 3
         * receiver : 龙少阁主
         * receiveArea : 南京市鼓楼区
         * detailsAddress : 南京市鼓楼区中山东路
         * phone : 13813858204
         * isDefault : 1
         * userId : 45
         * status : 0
         * createTime : 1529562331000
         * updateTime : 1529562331000
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

    public static class OrderGoodsBean {
        /**
         * shopCarId : 1
         * goodsId : 1
         * orderId : null
         * receiverId : null
         * title : 正宗地道  东北坚果
         * introduction : 185gx2袋
         * orderNumber : null
         * status : null
         * unitPrice : 36.90
         * goodsNumber : 2
         * totalPrice : 73.80
         * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
         * createTime : null
         * updateTime : null
         */

        private int shopCarId;
        private int goodsId;
        private Object orderId;
        private Object receiverId;
        private String title;
        private String introduction;
        private Object orderNumber;
        private Object status;
        private String unitPrice;
        private int goodsNumber;
        private String totalPrice;
        private String imgUrl;
        private Object createTime;
        private Object updateTime;

        public int getShopCarId() {
            return shopCarId;
        }

        public void setShopCarId(int shopCarId) {
            this.shopCarId = shopCarId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public Object getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(Object receiverId) {
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

        public Object getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(Object orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}
