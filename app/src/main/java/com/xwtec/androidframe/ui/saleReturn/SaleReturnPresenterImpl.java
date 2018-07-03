package com.xwtec.androidframe.ui.saleReturn;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/7/1.
 * Describe:xxx
 */

public class SaleReturnPresenterImpl extends BasePresenter<SaleReturnContact.SaleReturnView> implements SaleReturnContact.SaleReturnPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public SaleReturnPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void saleReturn(RequestBody body) {
        netResourceRepo.salesReturn(body)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.saleReturnSuccess();
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
