package com.xwtec.androidframe.ui.myOrders.bean;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */

public class Order {

    /**
     * goodsId : 1
     * orderId : 8
     * status : 3
     * title : 正宗地道  东北坚果
     * introduction : 185gx2袋
     * orderNumber : 62568964512808960
     * unitPrice : 36.90
     * goodsNumber : 2
     * totalPrice : 73.80
     * expressPrice : 0.00
     */

    private int goodsId;
    private int orderId;
    private int status;
    private String title;
    private String introduction;
    private String orderNumber;
    private String unitPrice;
    private int goodsNumber;
    private String totalPrice;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }
}
