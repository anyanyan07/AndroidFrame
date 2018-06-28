package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public class CreateAddPresenterImpl extends BasePresenter<CreateAddContact.CreateAddView> implements CreateAddContact.CreateAddPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public CreateAddPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void createAdd(RequestBody body) {
        netResourceRepo.addAddress(body)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            view.createSuccess(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void updateAddress(RequestBody body) {
        netResourceRepo.updateAddress(body)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            view.updateSuccess(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
