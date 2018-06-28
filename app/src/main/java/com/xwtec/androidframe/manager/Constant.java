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
    public static final String PERSONAL_ROUTER = "/activity/personal";
    public static final String SETTING_ROUTER = "/activity/setting";
    public static final String UPDATE_PASSWORD_ROUTER = "/activity/updatePwd";
    public static final String ADDRESS_ROUTER = "/activity/address";
    public static final String CREATE_ADDRESS_ROUTER = "/activity/createAddress";
    public static final String SHOP_CART_FRAG = "/fragment/shopCart";
    public static final String MINE_FRAG = "/fragment/mine";
    public static final String GOODS_DETAIL_ROUTER = "/activity/goodDetail";
    public static final String MY_ORDER_ROUTER = "/activity/myOrder";
    public static final String CLASSIFY_LIST_ROUTER = "/activity/classifyList";
    public static final String AFFIRM_ORDER_ROUTER = "/activity/affirmOrder";
    public static final String PAY_ROUTER = "/activity/pay";
    //********页面路由路径管理**********
    public static final int FIRST_PAGE_INDEX = 0;//分页加载时首页页码
    public static final int PER_PAGE_NUM = 20;//分页加载时每页数量
    //短信验证码接口type:0注册验证码,1找回密码,2修改密码,3登录
    public static final int REGISTER_VERIFY_TYPE = 0;
    public static final int FINDBACK_VERIFY_TYPE = 1;
    public static final int UPDATE_PASSWORD_VERIFY_TYPE = 2;
    public static final int LOGIN_VERIFY_TYPE = 3;

    public static final String USER_KEY = "USER_KEY";

    public static final String RX_LOGIN_SUCCESS = "RX_LOGIN_SUCCESS";//登录成功发送RxBux消息
    public static final String RX_LOGOUT = "RX_LOGOUT";//退出登录发送RxBux消息

    public static final String ORDER_STATUS = "ORDER_STATUS";
    /**
     * 0代表订单完成;1代表已删除;2代表待付款3代表已付款与待发货;4代表已发货与路途中;
     * 5代表已收货;6用户确认收货;7代表退货中;8代表已退货与退货完成;9代表取消订单中；
     * 10代表已取消与取消完成；11退款中;12已退款）不传代表查询全部订单
     */
    public static final int FINISHED = 0;//已完成
    public static final int DELETED = 1;//已删除
    public static final int WAIT_PAY = 2;//待付款
    public static final int PAIED_WAIT_SEND = 3;//已付款与待发货
    public static final int SENDED = 4;//已发货与路途中
    public static final int RECEIVED = 5;//已收货
    public static final int SURE_RECEIVED = 6;//确认收货
    public static final int SALE_RETURNING = 7;//退货中
    public static final int SALE_RETURNED = 8;//已退货与退货完成
    public static final int CANCELING = 9;//取消订单中
    public static final int CANCELED = 10;//已取消与取消完成
    public static final int REFUNDING = 11;//退款中
    public static final int REFUNDED = 12;//已退款

}
