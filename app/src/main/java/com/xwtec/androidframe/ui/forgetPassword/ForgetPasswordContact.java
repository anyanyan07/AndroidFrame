package com.xwtec.androidframe.ui.forgetPassword;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

/**
 * Created by ayy on 2018/6/20.
 * Describe:xxx
 */

public interface ForgetPasswordContact {
    interface ForgetPasswordView extends BaseView {
        void resetSuccess(String msg);

        void sendCodeSuccess(String msg);
    }

    interface ForgetPasswordPresenter {
        void resetPassword(HashMap<String, Object> map);

        void sendVerifyCode(HashMap<String, Object> map);
    }
}
