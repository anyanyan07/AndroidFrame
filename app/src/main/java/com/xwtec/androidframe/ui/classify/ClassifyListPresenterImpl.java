package com.xwtec.androidframe.ui.classify;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/26.
 * Describe:xxx
 */

public class ClassifyListPresenterImpl extends BasePresenter<ClassifyListContact.ClassifyListView> implements ClassifyListContact.ClassifyListPresenter {

    private NetResourceRepo netResourceRepo;

    @Inject
    public ClassifyListPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchCategoryContent(HashMap<String, Object> map) {
        netResourceRepo.fetchCategoryContent(map)
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
