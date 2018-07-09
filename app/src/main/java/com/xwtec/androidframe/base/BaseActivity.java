package com.xwtec.androidframe.base;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.wang.avi.AVLoadingIndicatorView;
import com.xwtec.androidframe.R;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:所有activity继承的基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SuperBaseActivity implements BaseView {
    @Inject
    public T presenter;
    private Dialog loadingDialog;
    private AVLoadingIndicatorView loadingView;

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
        if (loadingDialog == null) {
            loadingDialog = new Dialog(this, R.style.loading);
            loadingDialog.setCanceledOnTouchOutside(false);
            View view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null);
            loadingView = view.findViewById(R.id.av_loading);
            loadingDialog.setContentView(view);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
            loadingView.setVisibility(View.VISIBLE);
            loadingView.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingView.hide();
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showLoadFail(String msg) {
        dismissLoading();
        ToastUtils.showShort(msg);
    }
}
