package com.xwtec.androidframe.ui.home;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

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
                        if (baseResponse.getCode()==0){
                            if (view != null) {
                                view.bannerSuccess(baseResponse.getContent());
                            }
                        }else{
                            if (view != null) {
                                view.fail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.fail("请求banner失败");
                        }
                    }
                });
    }

    @Override
    public void getGoodList() {

    }
}
