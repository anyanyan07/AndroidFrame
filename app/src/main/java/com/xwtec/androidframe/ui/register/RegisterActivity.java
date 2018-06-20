package com.xwtec.androidframe.ui.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.REGISTER_ROUTER)
public class RegisterActivity extends BaseActivity<RegisterPresenterImpl> implements RegisterContact.RegisterView {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;

    @Override
    protected void init() {
        super.init();
        ivLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("注册");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    @OnClick({R.id.btn_register, R.id.tv_get_verify_code, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_get_verify_code:
                break;
            default:
                break;
        }
    }

    private void register() {
        String phoneNum = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            ToastUtils.showShort("请输入正确的手机号");
            return;
        }
        String verifyCode = etVerifyCode.getText().toString().trim();
        if (TextUtils.isEmpty(verifyCode)) {
            ToastUtils.showShort("请输入验证码");
            return;
        }
        String passowrd = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(passowrd)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        String passwordAgain = etPasswordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(passwordAgain)) {
            ToastUtils.showShort("请再次输入密码");
            return;
        }
        if (!passowrd.equals(passwordAgain)) {
            ToastUtils.showShort("两次密码输入不一致");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("vp", phoneNum);
        map.put("vpw", verifyCode);
        presenter.register(map);
    }

    @Override
    public void registerSuccess(RegisterResponseBean registerResponseBean) {
        ToastUtils.showShort("注册成功");
    }
}
