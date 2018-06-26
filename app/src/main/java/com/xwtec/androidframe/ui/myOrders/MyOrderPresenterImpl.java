package com.xwtec.androidframe.ui.myOrders;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.myOrders.bean.Order;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */

public class MyOrderPresenterImpl extends BasePresenter<MyOrderContact.MyOrderView> implements MyOrderContact.MyOrderPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public MyOrderPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchOrders(HashMap<String, Object> map) {
        netResourceRepo.fetchOrders(map)
                .subscribe(new ResponseObserver<BaseResponse<List<Order>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<Order>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchOrdersSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.showLoadFail(e.getMessage());
                        }
                    }
                });
    }
}
