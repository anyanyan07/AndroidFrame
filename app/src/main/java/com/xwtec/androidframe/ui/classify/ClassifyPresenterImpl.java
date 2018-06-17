package com.xwtec.androidframe.ui.classify;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class ClassifyPresenterImpl extends BasePresenter<ClassifyContact.ClassifyView> implements ClassifyContact.ClassifyPresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public ClassifyPresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


    @Override
    public void fetchCategories() {
        mNetResourceRepo.fetchCategories()
                .subscribe(new ResponseObserver<BaseResponse<List<CategoryBean>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<CategoryBean>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.fetchCategoriesSuccess(baseResponse.getContent());
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
    public void fetchCategoryContent(HashMap<String, Object> map) {
        mNetResourceRepo.fetchCategoryContent(map)
                .subscribe(new ResponseObserver<BaseResponse<List<CategoryContentBean>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<CategoryContentBean>> baseResponse) {
                        if (view != null) {
                            if (baseResponse.getCode() == 0) {
                                view.fetchContentSuccess(baseResponse.getContent());
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
