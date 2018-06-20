package com.xwtec.androidframe.ui.forgetPassword;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.FORGET_PASSWORD_ROUTER)
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenterImpl> implements ForgetPasswordContact.ForgetPasswordView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.forgetPassword);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @OnClick({R.id.iv_left, R.id.tv_get_verify_code, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_get_verify_code:
                break;
            case R.id.btn_submit:
                break;
        }
    }
}
