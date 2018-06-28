package com.xwtec.androidframe.ui.affirmOrder;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.ui.affirmOrder.bean.SubmitOrderBean;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/27.
 * Describe:确认订单
 */

public interface AffirmOrderContact {
    interface AffirmOrderView extends BaseView {
        void affirmSuccess(AffirmResponse affirmResponse);

        void submitSuccess(SubmitOrderBean submitOrderBean);
    }

    interface AffirmOrderPresenter {
        void affirmOrder(RequestBody body);

        void submitOrder(RequestBody body);
    }
}
