package com.xwtec.androidframe.base;

import com.blankj.utilcode.util.ToastUtils;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有activity继承的基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SuperBaseActivity implements BaseView {
    @Inject
    public T presenter;


    @Override
    protected void init() {
        presenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoadFail(String msg) {
        ToastUtils.showShort(msg);
    }
}
