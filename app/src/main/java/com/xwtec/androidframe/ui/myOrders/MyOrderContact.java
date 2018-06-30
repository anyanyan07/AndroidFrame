package com.xwtec.androidframe.ui.myOrders;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.myOrders.bean.Order;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */

public interface MyOrderContact {
    interface MyOrderView extends BaseView {
        void fetchOrdersSuccess(List<Order> orders);

        void deleteSuccess(int position);

        void cancelSuccess(int position);

        void sureReceiveSuccess(int position);
    }

    interface MyOrderPresenter {
        void fetchOrders(HashMap<String, Object> map);

        void deleteOrder(String orderIds, String token, int position);

        void cancelOrder(long orderId, String token, int position);

        void sureReceive(String orderId, String token,int position);
    }
}
