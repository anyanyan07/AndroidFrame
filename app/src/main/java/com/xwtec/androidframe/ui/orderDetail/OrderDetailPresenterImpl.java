package com.xwtec.androidframe.ui.orderDetail;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.orderDetail.bean.CanceledInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.FinishedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.ReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SendedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SureReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitPayInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitSendInfo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/29.
 * Describe:xxx
 */

public class OrderDetailPresenterImpl extends BasePresenter<OrderDetailContact.OrderDetailView> implements OrderDetailContact.OrderDetailPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public OrderDetailPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchWaitPayInfo(long orderId, String token) {
        netResourceRepo.fetchWaitPayInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<WaitPayInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<WaitPayInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchInfoSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (view != null) {
                            view.showLoadFail(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void cancelOrder(long orderId, String token) {
        netResourceRepo.cancelOrder(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.cancelSuccess();
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchFinishedInfo(long orderId, String token) {
        netResourceRepo.fetchFinishedInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<FinishedInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<FinishedInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchFinishedSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
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
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchCanceledInfo(long orderId, String token) {
        netResourceRepo.fetchCanceledInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<CanceledInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<CanceledInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchCanceledSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void sureReceive(String orderId, String token) {
        netResourceRepo.sureReceive(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.sureReceiveSuccess();
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchSendedInfo(long orderId, String token) {
        netResourceRepo.fetchSendedInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<SendedInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<SendedInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchSendedSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchReceivedInfo(long orderId, String token) {
        netResourceRepo.fetchReceivedInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<ReceivedInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<ReceivedInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchReceivedSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchWaitSendInfo(long orderId, String token) {
        netResourceRepo.fetchWaitSendInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<WaitSendInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<WaitSendInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchWaitSendSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchSureReceivedInfo(long orderId, String token) {
        netResourceRepo.fetchSureReceivedInfo(orderId, token)
                .subscribe(new ResponseObserver<BaseResponse<SureReceivedInfo>>(this) {
                    @Override
                    public void onNext(BaseResponse<SureReceivedInfo> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchSureReceivedSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
