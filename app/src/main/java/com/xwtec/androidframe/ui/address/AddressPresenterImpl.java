package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.RequestBody;


/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public class AddressPresenterImpl extends BasePresenter<AddressContact.AddressView> implements AddressContact.AddressPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public AddressPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void queryAddress(String token) {
        netResourceRepo.queryAddress(token)
                .subscribe(new ResponseObserver<BaseResponse<List<Address>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<Address>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.querySuccess(baseResponse.getContent());
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
    public void delAdd(HashMap<String, Object> map, final int position) {
        netResourceRepo.deleteAddress(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.delSuccess(position);
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
