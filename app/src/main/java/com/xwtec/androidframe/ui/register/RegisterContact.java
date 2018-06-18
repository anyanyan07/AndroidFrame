package com.xwtec.androidframe.ui.register;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public interface RegisterContact {
    interface RegisterView extends BaseView {
        void registerSuccess(RegisterResponseBean registerResponseBean);
    }

    interface RegisterPresenter {
        void register(HashMap<String, Object> map);
    }
}
