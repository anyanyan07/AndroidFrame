package com.xwtec.androidframe.ui.refundDetail;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturnedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturningInfo;

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

    @Override
    public void fetchRefundedInfo(long orderId, String token) {
        netResourceRepo.fetchRefundedInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<RefundedInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<RefundedInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchRefundedSuccess(baseResponse.getContent());
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

    @Override
    public void fetchSaleReturnedInfo(long orderId, String token) {
        netResourceRepo.fetchSaleReturnedInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<SalesReturnedInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<SalesReturnedInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchSaleReturnedSuccess(baseResponse.getContent());
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

    @Override
    public void fetchSaleReturningInfo(long orderId, String token) {
        netResourceRepo.fetchSaleReturningInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<SalesReturningInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<SalesReturningInfo> baseResponse) {
                        if (view != null) {
                            view.fetchSaleReturningSuccess(baseResponse.getContent());
                        } else {
                            view.showLoadFail(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void deleteOrder(String orderIds, String token) {
        netResourceRepo.deleteOrder(orderIds, token)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.deleteSuccess();
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
