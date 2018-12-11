package com.xwtec.androidframe.ui.expressInfo;

import com.xwtec.androidframe.base.BaseView;

import java.util.List;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */

public interface ExpressInfoContact {
    interface ExpressInfoView extends BaseView{
        void fetchExpressInfoSuccess(List<ExpressInfo> expressInfoList);
    }
    interface ExpressInfoPresenter{
        void fetchExpressInfo(String orderNum,String token);
    }
}
