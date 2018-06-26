package com.xwtec.androidframe.ui.personalInfo;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/21.
 * Describe:xxx
 */

public interface PersonalContact {
    interface PersonalView extends BaseView {
        void updateSuccess(String msg);

        void uploadHeaderSuccess();
    }

    interface PersonalPresenter {
        void updatePersonalInfo(RequestBody requestBody);

        void uploadHeader(HashMap<String, Object> map, MultipartBody.Part file);
    }
}
