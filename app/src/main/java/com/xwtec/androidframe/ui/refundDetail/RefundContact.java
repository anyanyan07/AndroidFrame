package com.xwtec.androidframe.ui.refundDetail;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;

/**
 * Created by ayy on 2018/6/30.
 * Describe:退款详情
 */

public interface RefundContact {
    interface RefundView extends BaseView {
        void fetchRefundingSuccess(RefundingInfo refundingInfo);

    }

    interface RefundPresenter {

        void fetchRefundingInfo(long orderId, String token);
    }
}
