package com.xwtec.androidframe.ui.moneyReturn;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/7/2.
 * Describe:退款
 */

public class MoneyReturnPresenterImpl extends BasePresenter<MoneyReturnContact.MoneyReturnView> implements MoneyReturnContact.MoneyReturnPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public MoneyReturnPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void moneyReturn(HashMap<String, Object> map) {
        netResourceRepo.moneyReturn(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.moneyReturnSuccess();
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
