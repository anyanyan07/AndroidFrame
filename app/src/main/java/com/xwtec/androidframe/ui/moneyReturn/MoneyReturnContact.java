package com.xwtec.androidframe.ui.moneyReturn;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

/**
 * Created by ayy on 2018/7/2.
 * Describe:退款
 */

public interface MoneyReturnContact {
    interface MoneyReturnView extends BaseView {
        void moneyReturnSuccess();
    }

    interface MoneyReturnPresenter {
        void moneyReturn(HashMap<String, Object> map);
    }
}
