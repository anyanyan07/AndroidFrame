package com.xwtec.androidframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.xwtec.androidframe.manager.AppManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有activity继承的基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView, HasSupportFragmentInjector {
    @Inject
    public T presenter;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingSupportFragmentInjector;

    private Unbinder unbinder;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        presenter.attachView(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.get().addActivity(this);
        unbinder =  ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        AppManager.get().finishActivity();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingSupportFragmentInjector;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoadFail(String msg) {

    }

    protected abstract void init();
    protected abstract int getLayoutId();
}
