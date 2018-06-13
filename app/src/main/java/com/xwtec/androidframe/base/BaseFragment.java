package com.xwtec.androidframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有fragment继承的基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{
    private Unbinder unbinder;
    protected Context context;

    @Inject
    protected T presenter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder =  ButterKnife.bind(this,view);
        presenter.attachView(this);
        init();
        return view;
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
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
}
