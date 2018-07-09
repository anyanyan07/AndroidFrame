package com.xwtec.androidframe.ui.updatePassword;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

/**
 * Created by ayy on 2018/6/22.
 * Describe:xxx
 */

public interface UpdatePasswordContact {
    interface UpdatePasswordView extends BaseView {
        void sendCodeSuccess(String msg);

        void sendCodeFail(String msg);

        void updateSuccess(String msg);
    }

    interface UpdatePasswordPresenter {
        void sendVerifyCode(HashMap<String, Object> map);

        void updatePassword(HashMap<String, Object> map);
    }
}
