package com.xwtec.androidframe.ui.goodDetail;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public class GoodDetailPresenterImpl extends BasePresenter<GoodDetailContact.GoodDetailView> implements GoodDetailContact.GoodDetailPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public GoodDetailPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchGoodDetail(long goodId) {
        netResourceRepo.fetchGoodDetail(goodId)
                .subscribe(new ResponseObserver<BaseResponse<GoodDetailResponse>>(this) {
                    @Override
                    public void onNext(BaseResponse<GoodDetailResponse> baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.fetchGoodDetailSuccess(baseResponse.getContent());
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
    public void addShopCart(HashMap<String, Object> map) {
        netResourceRepo.addShopCart(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.addShopCartSuccess();
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
    public void fetchComment(long goodId, int commentLevel, int startIndex, int showNum) {
        netResourceRepo.fetchGoodComment(goodId,commentLevel,startIndex,showNum)
                .subscribe(new ResponseObserver<BaseResponse<List<CommentInfo>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<CommentInfo>> baseResponse) {
                        if (view != null){
                            if (baseResponse.isSuccess()){
                                view.fetchCommentSuccess(baseResponse.getContent());
                            }
                        }
                    }
                });
    }
}
