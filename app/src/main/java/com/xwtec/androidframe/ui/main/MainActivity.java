package com.xwtec.androidframe.ui.main;

import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainConcat.MainView {


    @BindView(R.id.tv_result)
    TextView tvResult;



    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showShort("登录成功");
    }

    @Override
    public void loginFail() {
        ToastUtils.showShort("登录失败");
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        Map<String,String> map =  new HashMap<>();
        map.put("link_mobile","15912460032");
        map.put("v_code","888888");
        presenter.login(map);
    }
}
