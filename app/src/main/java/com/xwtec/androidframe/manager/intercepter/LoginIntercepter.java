package com.xwtec.androidframe.manager.intercepter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;

/**
 * Created by ayy on 2018/6/24.
 * Describe:登录拦截
 */

public class LoginIntercepter {
    public static boolean intercepter(String target) {
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean == null) {//未登录，跳转到登录
            ARouter.getInstance()
                    .build(Constant.LOGIN_ROUTER)
                    .withString("target", target)
                    .navigation();
            return true;
        }
        return false;
    }
}
