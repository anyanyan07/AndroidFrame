package com.xwtec.androidframe.base;

import com.blankj.utilcode.util.CacheUtils;
import com.xwtec.androidframe.manager.ApiException;
import com.xwtec.androidframe.manager.Constant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public abstract class ResponseObserver<T> implements Observer<T> {

    private BasePresenter presenter;

    protected ResponseObserver(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        presenter.addSubscribe(d);
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            switch (apiException.getCode()) {
                case 2://登录过期
                    CacheUtils.getInstance().remove(Constant.USER_KEY);
//                    ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
                    break;
            }
        }
    }
}
