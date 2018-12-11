package com.xwtec.androidframe.ui.personalInfo;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.login.UserBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/21.
 * Describe:xxx
 */

public interface PersonalContact {
    interface PersonalView extends BaseView {
        void updateSuccess(String msg);

        void uploadHeaderSuccess(String headerUrl);

        void fetchUserInfoSuccess(UserBean userBean);
    }

    interface PersonalPresenter {
        void updatePersonalInfo(RequestBody requestBody);

        void uploadHeader(RequestBody token, MultipartBody.Part file);

        void fetchUserInfo(String token);
    }
}
