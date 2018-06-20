package com.xwtec.androidframe.ui.home;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.TabBean;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class HomePresenterImpl extends BasePresenter<HomeContact.HomeView> implements HomeContact.HomePresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public HomePresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


    @Override
    public void getHomeBanner() {
        mNetResourceRepo.getHomeBanner(3)
                .subscribe(new ResponseObserver<BaseResponse<List<BannerBean>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<BannerBean>> baseResponse) {
                        if (baseResponse.getCode() == 0) {
                            if (view != null) {
                                view.bannerSuccess(baseResponse.getContent());
                            }
                        } else {
                            if (view != null) {
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
    public void getGoodList(HashMap<String, Object> map) {
        mNetResourceRepo.getGoodList(map)
                .subscribe(new ResponseObserver<BaseResponse<List<GoodListBean>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<GoodListBean>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.goodListSuccess(baseResponse.getContent());
                            } else {
                                view.goodListFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.goodListFail(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void fetchGoodDefines() {
        mNetResourceRepo.fetchGoodDefines()
                .subscribe(new ResponseObserver<BaseResponse<List<TabBean>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<TabBean>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchGoodDefinesSuccess(baseResponse.getContent());
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
