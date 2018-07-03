package com.xwtec.androidframe.ui.express;

import com.xwtec.androidframe.base.BaseView;

import java.util.List;

/**
 * Created by ayy on 2018/7/1.
 * Describe:xxx
 */

public interface ExpressContact {
    interface ExpressView extends BaseView {
        void fetchExpressSuccess(List<Express> expressList);
    }

    interface ExpressPresenter {
        void fetchExpressList();
    }
}
