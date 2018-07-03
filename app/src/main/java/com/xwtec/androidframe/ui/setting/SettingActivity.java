package com.xwtec.androidframe.ui.setting;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.SETTING_ROUTER)
public class SettingActivity extends BaseActivity<SettingPresenterImpl> implements SettingContact.SettingView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cache_size)
    TextView tvCacheSize;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.setting);
        tvCacheSize.setText(CacheUtils.getInstance().getCacheSize() / 1000 / 1024 + "");
        tvVersion.setText(AppUtils.getAppVersionName());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void logoutSuccess(String msg) {
        ToastUtils.showShort(msg);
        CacheUtils.getInstance().remove(Constant.USER_KEY);
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_LOGOUT, ""));
        finish();
    }

    @OnClick({R.id.iv_left, R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_logout:
                UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
                if (userBean != null) {
                    presenter.logout(userBean.getToken());
                }
                break;
        }
    }
}
