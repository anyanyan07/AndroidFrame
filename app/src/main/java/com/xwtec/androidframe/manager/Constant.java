package com.xwtec.androidframe.manager;

/**
 * Created by ayy on 2018/5/28.
 * Describe:常量类
 */

public class Constant {
    public static final String BASE_URL = "http://118.31.11.130:8080/farm/";

    //********页面路由路径管理**********
    public static final String REGISTER_ROUTER = "/activity/register";
    public static final String LOGIN_ROUTER = "/activity/login";
    public static final String MAIN_ROUTER = "/activity/main";
    public static final String FORGET_PASSWORD_ROUTER = "/activity/forget";
    public static final String HELP_ROUTER = "/activity/help";
    public static final String FEEDBACK_ROUTER = "/activity/feedback";
    public static final String CONTACT_SERVICE_ROUTER = "/activity/contact";
    //********页面路由路径管理**********
    public static final int FIRST_PAGE_INDEX = 0;//分页加载时首页页码
    public static final int PER_PAGE_NUM = 20;//分页加载时每页数量
    //短信验证码接口type:0注册验证码,1找回密码,2修改密码,3登录
    public static final int REGISTER_VERIFY_TYPE = 0;
    public static final int FINDBACK_VERIFY_TYPE = 1;
    public static final int UPDATE_PASSWORD_VERIFY_TYPE = 2;
    public static final int LOGIN_VERIFY_TYPE = 3;

}
