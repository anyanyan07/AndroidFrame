package com.xwtec.androidframe.ui.goodDetail.bean;

import java.util.List;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public class GoodDetailResponse {

    /**
     * id : 1
     * title : 正宗地道  东北坚果
     * introduction : 185gx2袋
     * discountPrice : 36.90
     * originalPrice : 81.00
     * detailImgList : [{"id":1,"goodsId":1,"sort":1,"status":0,"createTime":1528782775000,"updateTime":1528782777000,"imgUrl":"https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg"}]
     * detailImgTextList : [{"id":1,"goodsId":1,"sort":1,"status":0,"createTime":1528782790000,"updateTime":1528782792000,"imgUrl":"https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg"}]
     */

    private long id;
    private String title;
    private String introduction;
    private String discountPrice;
    private String originalPrice;
    private List<DetailImgListBean> detailImgList;
    private List<DetailImgTextListBean> detailImgTextList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<DetailImgListBean> getDetailImgList() {
        return detailImgList;
    }

    public void setDetailImgList(List<DetailImgListBean> detailImgList) {
        this.detailImgList = detailImgList;
    }

    public List<DetailImgTextListBean> getDetailImgTextList() {
        return detailImgTextList;
    }

    public void setDetailImgTextList(List<DetailImgTextListBean> detailImgTextList) {
        this.detailImgTextList = detailImgTextList;
    }

    public static class DetailImgListBean {
        /**
         * id : 1
         * goodsId : 1
         * sort : 1
         * status : 0
         * createTime : 1528782775000
         * updateTime : 1528782777000
         * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
         */

        private int id;
        private int goodsId;
        private int sort;
        private int status;
        private long createTime;
        private long updateTime;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

    public static class DetailImgTextListBean {
        /**
         * id : 1
         * goodsId : 1
         * sort : 1
         * status : 0
         * createTime : 1528782790000
         * updateTime : 1528782792000
         * imgUrl : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/IMG_20140530_081246.jpg
         */

        private int id;
        private int goodsId;
        private int sort;
        private int status;
        private long createTime;
        private long updateTime;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
