package com.xwtec.androidframe.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有presenter继承的基类
 * T:View的泛型
 */

public class BasePresenter<T extends BaseView> {
    protected T view;
    private CompositeDisposable mCompositeDisposable;
    //依附View
    void attachView(T view){
        this.view = view;
    }

    //释放View,中断所有网络传输
    void detachView(){
        this.view = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }


}
