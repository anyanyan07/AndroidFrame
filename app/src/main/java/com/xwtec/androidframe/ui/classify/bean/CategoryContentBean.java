package com.xwtec.androidframe.ui.classify.bean;

/**
 * Created by ayy on 2018/6/16.
 * Describe:xxx
 */

public class CategoryContentBean {

    /**
     * id : 1
     * title : 正宗地道  东北坚果
     * introduction : 185gx2袋
     * discountPrice : 36.90
     * originalPrice : 81.00
     * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
     */

    private int id;
    private String title;
    private String introduction;
    private String discountPrice;
    private String originalPrice;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
