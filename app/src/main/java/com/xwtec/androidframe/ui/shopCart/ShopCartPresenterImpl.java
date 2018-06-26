package com.xwtec.androidframe.ui.shopCart;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class ShopCartPresenterImpl extends BasePresenter<ShopCartContact.ShopCartView> implements ShopCartContact.ShopCartPresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public ShopCartPresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


    @Override
    public void fetchShopCartData(HashMap<String, Object> map) {
        mNetResourceRepo.fetchShopCartData(map)
                .subscribe(new ResponseObserver<BaseResponse<List<ShopCartBean>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<ShopCartBean>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0)
                                view.fetchShopCartSuccess(baseResponse.getContent());
                        } else {
                            view.showLoadFail(baseResponse.getMsg());
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

    @Override
    public void updateShopCart(final HashMap<String, Object> map, final ShopCartBean shopCartBean, final int position) {
        mNetResourceRepo.updateShopCart(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.updateShopCartSuccess(shopCartBean, (Integer) map.get("goodsNumber"), position);
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

    @Override
    public void deleteShopCart(String ids,String token) {
        mNetResourceRepo.deleteFromShopCart(ids,token)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.deleteFromShopCartSuccess();
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
