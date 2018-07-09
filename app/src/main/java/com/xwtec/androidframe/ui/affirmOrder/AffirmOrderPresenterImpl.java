package com.xwtec.androidframe.ui.affirmOrder;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.ui.affirmOrder.bean.SubmitOrderBean;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/27.
 * Describe:xxx
 */

public class AffirmOrderPresenterImpl extends BasePresenter<AffirmOrderContact.AffirmOrderView> implements AffirmOrderContact.AffirmOrderPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public AffirmOrderPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void affirmOrder(RequestBody body) {
        netResourceRepo.affirmOrder(body)
                .subscribe(new ResponseObserver<BaseResponse<AffirmResponse>>(this) {
                    @Override
                    public void onNext(BaseResponse<AffirmResponse> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.affirmSuccess(baseResponse.getContent());
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
    public void submitOrder(RequestBody body) {
        netResourceRepo.submitOrder(body)
                .subscribe(new ResponseObserver<BaseResponse<SubmitOrderBean>>(this) {
                    @Override
                    public void onNext(BaseResponse<SubmitOrderBean> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.submitSuccess(baseResponse.getContent());
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
}
