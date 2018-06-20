package com.xwtec.androidframe.ui.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.LOGIN_ROUTER)
public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements LoginContact.LoginView {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.login);
        ivLeft.setVisibility(View.VISIBLE);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.tv_get_verify_code, R.id.tv_forget_password, R.id.tv_register, R.id.btn_login, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_verify_code:
                break;
            case R.id.tv_forget_password:
                ARouter.getInstance().build(Constant.FORGET_PASSWORD_ROUTER).navigation();
                break;
            case R.id.tv_register:
                ARouter.getInstance().build(Constant.REGISTER_ROUTER).navigation();
                break;
            case R.id.btn_login:
                break;
            case R.id.iv_left:
                finish();
                break;
            default:
                break;
        }
    }
}
