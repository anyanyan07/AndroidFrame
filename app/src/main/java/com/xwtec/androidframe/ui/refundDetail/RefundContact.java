package com.xwtec.androidframe.ui.refundDetail;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturnedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturningInfo;

/**
 * Created by ayy on 2018/6/30.
 * Describe:退款详情
 */

public interface RefundContact {
    interface RefundView extends BaseView {
        void fetchRefundingSuccess(RefundingInfo refundingInfo);

        void fetchRefundedSuccess(RefundedInfo refundingInfo);

        void fetchSaleReturnedSuccess(SalesReturnedInfo salesReturnedInfo);

        void fetchSaleReturningSuccess(SalesReturningInfo salesReturningInfo);

        void deleteSuccess();

    }

    interface RefundPresenter {

        void fetchRefundingInfo(long orderId, String token);

        void fetchRefundedInfo(long orderId, String token);

        void fetchSaleReturnedInfo(long orderId, String token);

        void fetchSaleReturningInfo(long orderId, String token);

        void deleteOrder(String orderIds, String token);
    }
}
