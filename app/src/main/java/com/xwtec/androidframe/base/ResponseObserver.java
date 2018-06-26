package com.xwtec.androidframe.base;

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
}
