package com.xwtec.androidframe.ui.forgetPassword;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.TimerUtil;

import java.util.HashMap;

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
    @BindView(R.id.tv_get_verify_code)
    TextView tvSendCode;

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
                sendVerifyCode();
                break;
            case R.id.btn_submit:
                resetPassword();
                break;
        }
    }

    private void sendVerifyCode() {
        String phoneNum = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
            ToastUtils.showShort("请输入正确的手机号");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("vp", phoneNum);
        map.put("type", Constant.FINDBACK_VERIFY_TYPE);
        presenter.sendVerifyCode(map);
    }

    private void resetPassword() {
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
        map.put("code", verifyCode);
        map.put("newVpw", passowrd);
        map.put("newRvpw", passwordAgain);
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean != null) {
            map.put("token", userBean.getToken());
        }
        presenter.resetPassword(map);
    }

    @Override
    public void resetSuccess(String msg) {
        ToastUtils.showShort(msg);
        finish();
    }

    @Override
    public void sendCodeSuccess(String msg) {
        ToastUtils.showShort(R.string.sendVerifyCodeSuccess);
        TimerUtil.getInstance().startTimer(tvSendCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TimerUtil.getInstance().cancelTimer();
    }
}
