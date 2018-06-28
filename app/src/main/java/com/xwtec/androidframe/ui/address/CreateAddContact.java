package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BaseView;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public interface CreateAddContact {
    interface CreateAddView extends BaseView {
        void createSuccess(String msg);

        void updateSuccess(String msg);
    }

    interface CreateAddPresenter {
        void createAdd(RequestBody body);

        void updateAddress(RequestBody body);
    }
}
