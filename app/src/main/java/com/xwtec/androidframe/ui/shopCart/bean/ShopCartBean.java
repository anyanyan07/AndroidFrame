package com.xwtec.androidframe.ui.shopCart.bean;

/**
 * Created by ayy on 2018/6/17.
 * Describe:购物车Bean
 */

public class ShopCartBean {

    /**
     * id : 2
     * userId : 1
     * goodsId : 1
     * goodsNumber : 2
     * title : 正宗地道  东北坚果
     * introduction : 185gx2袋
     * discountPrice : 36.90
     * originalPrice : 81.00
     * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
     */

    private int id;
    private int userId;
    private int goodsId;
    private int goodsNumber;
    private String title;
    private String introduction;
    private String discountPrice;
    private String originalPrice;
    private String imgUrl;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
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

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
