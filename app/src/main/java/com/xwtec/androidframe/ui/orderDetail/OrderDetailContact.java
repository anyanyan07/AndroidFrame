package com.xwtec.androidframe.ui.orderDetail;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.orderDetail.bean.CanceledInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.FinishedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.ReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SendedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SureReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitPayInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitSendInfo;

/**
 * Created by ayy on 2018/6/29.
 * Describe:xxx
 */

public interface OrderDetailContact {
    interface OrderDetailView extends BaseView {
        void fetchInfoSuccess(WaitPayInfo waitPayInfo);

        void fetchFinishedSuccess(FinishedInfo finishedInfo);

        void fetchCanceledSuccess(CanceledInfo canceledInfo);

        void fetchSendedSuccess(SendedInfo sendedInfo);

        void fetchReceivedSuccess(ReceivedInfo receivedInfo);

        void fetchWaitSendSuccess(WaitSendInfo waitSendInfo);

        void fetchSureReceivedSuccess(SureReceivedInfo sureReceivedInfo);

        void cancelSuccess();

        void deleteSuccess();

        void sureReceiveSuccess();
    }

    interface OrderDetailPresenter {
        void fetchWaitPayInfo(long orderId, String token);

        void fetchFinishedInfo(long orderId, String token);

        void fetchCanceledInfo(long orderId, String token);

        void fetchSendedInfo(long orderId, String token);

        void fetchReceivedInfo(long orderId, String token);

        void fetchWaitSendInfo(long orderId, String token);

        void fetchSureReceivedInfo(long orderId, String token);

        void cancelOrder(long orderId, String token);

        void deleteOrder(String orderIds, String token);

        void sureReceive(String orderId, String token);
    }
}
