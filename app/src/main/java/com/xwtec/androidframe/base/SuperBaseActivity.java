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
 * Created by ayy on 2018/6/20.
 * Describe:xxx
 */

public abstract class SuperBaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingSupportFragmentInjector;

    private Unbinder unbinder;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.get().addActivity(this);
        unbinder =  ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
        if (unbinder != null) {
            unbinder.unbind();
        }
        AppManager.get().finishActivity();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingSupportFragmentInjector;
    }

    protected abstract void init();
    protected void detachPresenter(){}
    protected abstract int getLayoutId();
}
