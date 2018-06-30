package com.xwtec.androidframe.ui.refundDetail;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/30.
 * Describe:xxx
 */

public class RefundPresenterImpl extends BasePresenter<RefundContact.RefundView> implements RefundContact.RefundPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public RefundPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchRefundingInfo(long orderId, String token) {
        netResourceRepo.fetchRefundingInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<RefundingInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<RefundingInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchRefundingSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
