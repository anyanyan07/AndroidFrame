package com.xwtec.androidframe.ui.login;

import java.io.Serializable;

/**
 * Created by ayy on 2018/6/20.
 * Describe:用户基本信息
 */

public class UserBean implements Serializable {


    /**
     * userId:45
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzAzNzUzNTMsInVzZXJJZCI6IjQ1IiwiaXNzIjoiU2VydmljZSIsImF1ZCI6IkFQUCIsImlhdCI6MTUyOTUxMTM1M30.UH8K3VqYi1jo49PNrMcM6iTwskErQpIlcpHi-ZVdTeE
     * headImg : https://ne-farm.oss-cn-beijing.aliyuncs.com/farm_img/head_img_81d86e273b7d4459b20eb15423f9c7f93page_03.jpg
     * nickName : null
     * sex : null
     * birth : null
     * integral : 100
     */

    private int userId;
    private String token;
    private String headImg;
    private String nickName;
    private String sex;
    private String birth;
    private int integral;

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
