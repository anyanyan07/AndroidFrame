package com.xwtec.androidframe.ui.refundDetail.bean;

/**
 * Created by ayy on 2018/7/2.
 * Describe:退货中
 */

public class SalesReturningInfo {

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
     * receiveAddress : {"id":1,"receiver":"龙三少","receiveArea":"南京江宁区","detailsAddress":"南京江宁区软件大道","phone":"13813858204","isDefault":0,"userId":45,"status":0,"createTime":1529549068000,"updateTime":1529561270000}
     * returnGoodsRecord : {"id":1,"orderId":9,"userId":45,"phone":"13813858204","orderNumber":"62568965490081792","cause":"货不好，过期了","status":0,"createTime":1529914385000,"updateTime":1529914332000}
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
    private ReceiveAddressBean receiveAddress;
    private ReturnGoodsRecordBean returnGoodsRecord;

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

    public ReceiveAddressBean getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(ReceiveAddressBean receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public ReturnGoodsRecordBean getReturnGoodsRecord() {
        return returnGoodsRecord;
    }

    public void setReturnGoodsRecord(ReturnGoodsRecordBean returnGoodsRecord) {
        this.returnGoodsRecord = returnGoodsRecord;
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

    public static class ReturnGoodsRecordBean {
        /**
         * id : 1
         * orderId : 9
         * userId : 45
         * phone : 13813858204
         * orderNumber : 62568965490081792
         * cause : 货不好，过期了
         * status : 0
         * createTime : 1529914385000
         * updateTime : 1529914332000
         */

        private int id;
        private int orderId;
        private int userId;
        private String phone;
        private String orderNumber;
        private String cause;
        private int status;
        private long createTime;
        private long updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
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
