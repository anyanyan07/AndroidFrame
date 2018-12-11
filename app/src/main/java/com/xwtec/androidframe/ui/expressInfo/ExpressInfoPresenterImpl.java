package com.xwtec.androidframe.ui.expressInfo;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.List;

import javax.inject.Inject;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */

public class ExpressInfoPresenterImpl extends BasePresenter<ExpressInfoContact.ExpressInfoView> implements ExpressInfoContact.ExpressInfoPresenter {

    private NetResourceRepo netResourceRepo;

    @Inject
    public ExpressInfoPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchExpressInfo(String orderNum, String token) {
        netResourceRepo.fetchExpressInfo(orderNum,token)
                .subscribe(new ResponseObserver<BaseResponse<List<ExpressInfo>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<ExpressInfo>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()){
                                view.fetchExpressInfoSuccess(baseResponse.getContent());
                            }
                        }
                    }
                });
    }
}
