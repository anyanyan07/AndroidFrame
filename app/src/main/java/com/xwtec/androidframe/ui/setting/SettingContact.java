package com.xwtec.androidframe.ui.setting;

import com.xwtec.androidframe.base.BaseView;

/**
 * Created by ayy on 2018/6/22.
 * Describe:xxx
 */

public interface SettingContact {
    interface SettingView extends BaseView {
        void logoutSuccess(String msg);
    }

    interface SettingPresenter {
        void logout(String token);
    }

}
