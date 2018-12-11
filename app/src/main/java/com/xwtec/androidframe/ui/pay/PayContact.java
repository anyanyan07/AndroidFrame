package com.xwtec.androidframe.ui.pay;

import com.xwtec.androidframe.base.BaseView;

import okhttp3.RequestBody;

/**
 * @Author ayy
 * @Date 2018/10/22.
 * Describe:xxx
 */

public interface PayContact {
    interface PayView extends BaseView{
        void paySuccess(PayBean payBean);
    }
    interface PayPresenter{
        void pay(RequestBody requestBody);
    }
}
