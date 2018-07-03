package com.xwtec.androidframe.ui.saleReturn;

import com.xwtec.androidframe.base.BaseView;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/7/1.
 * Describe:xxx
 */

public interface SaleReturnContact {
    interface SaleReturnView extends BaseView {
        void saleReturnSuccess();
    }

    interface SaleReturnPresenter {
        void saleReturn(RequestBody body);
    }
}
