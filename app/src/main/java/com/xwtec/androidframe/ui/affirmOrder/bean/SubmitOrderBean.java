package com.xwtec.androidframe.ui.affirmOrder.bean;

import java.util.List;

/**
 * Created by ayy on 2018/6/28.
 * Describe:提交订单返回数据
 */

public class SubmitOrderBean {


    /**
     * totalPrice : 251.80
     * orderNumbers : [{"orderId":27,"orderNumber":"64454595711074304"},{"orderId":28,"orderNumber":"64454611339051008"}]
     */

    private String totalPrice;
    private List<OrderNumbersBean> orderNumbers;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderNumbersBean> getOrderNumbers() {
        return orderNumbers;
    }

    public void setOrderNumbers(List<OrderNumbersBean> orderNumbers) {
        this.orderNumbers = orderNumbers;
    }

    public static class OrderNumbersBean {
        /**
         * orderId : 27
         * orderNumber : 64454595711074304
         */

        private int orderId;
        private String orderNumber;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }
    }
}
