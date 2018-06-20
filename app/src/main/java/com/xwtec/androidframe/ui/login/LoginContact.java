package com.xwtec.androidframe.ui.login;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

/**
 * Created by ayy on 2018/6/19.
 * Describe:xxx
 */

public interface LoginContact {
    interface LoginView extends BaseView {
        void sendCodeSuccess();

        void sendCodeFail(String msg);

        void loginSuccess(UserBean userBean);
    }

    interface LoginPresenter {
        void sendVerifyCode(HashMap<String, Object> map);

        void login(HashMap<String, Object> map);
    }
}
