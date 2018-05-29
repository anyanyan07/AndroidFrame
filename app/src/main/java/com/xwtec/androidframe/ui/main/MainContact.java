package com.xwtec.androidframe.ui.main;

import com.xwtec.androidframe.base.BaseView;

import java.util.Map;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface MainContact {
    interface MainView extends BaseView{
        void loginSuccess();

        void loginFail();
    }

    interface MainPresenter {
        void login(Map<String, String> map);
    }
}
