package com.xwtec.androidframe.ui.goodDetail.bean;

import java.util.List;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:评论列表
 */

public class CommentInfo {

    /**
     * userId : 45
     * nickName : 王兴龙
     * headImg : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/head_img_480711c7cf0d4a4493e24f97db1624d245.jpg
     * commentId : 1
     * stars : 5
     * comment : 好吃
     * commentDate : 1538878181000
     * imgUrlList : ["https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/goods_comment_img/goods_comment_img_fce74f8a1fab4e0b8b8afbc9f05274d41.jpg","https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/goods_comment_img/goods_comment_img_fce74f8a1fab4e0b8b8afbc9f05274d41.jpg"]
     */

    private int userId;
    private String nickName;
    private String headImg;
    private int commentId;
    private int stars;
    private String comment;
    private long commentDate;
    private List<String> imgUrlList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(long commentDate) {
        this.commentDate = commentDate;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }
}
