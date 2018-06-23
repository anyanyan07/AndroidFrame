package com.xwtec.androidframe.ui.personalInfo;

import com.xwtec.androidframe.base.BaseView;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/21.
 * Describe:xxx
 */

public interface PersonalContact {
    interface PersonalView extends BaseView {
        void updateSuccess(String msg);
    }

    interface PersonalPresenter {
        void updatePersonalInfo(RequestBody requestBody);
    }
}
