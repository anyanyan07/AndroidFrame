package com.xwtec.androidframe.ui.updatePassword;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.interfaces.SimpleTextWatcher;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.TimerUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.UPDATE_PASSWORD_ROUTER)
public class UpdatePasswordActivity extends BaseActivity<UpdatePwdPresenterImpl> implements UpdatePasswordContact.UpdatePasswordView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_get_verify_code)
    TextView tvSendCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private SimpleTextWatcher simpleTextWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            String phoneNum = etPhoneNum.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum) || phoneNum.length() != 11) {
                btnSubmit.setEnabled(false);
                return;
            }
            String verifyCode = etVerifyCode.getText().toString().trim();
            if (TextUtils.isEmpty(verifyCode)) {
                btnSubmit.setEnabled(false);
                return;
            }
            String password = etPassword.getText().toString().trim();
            if (TextUtils.isEmpty(password)) {
                btnSubmit.setEnabled(false);
                return;
            }
            btnSubmit.setEnabled(true);
        }
    };

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.updatePassword);
        etPhoneNum.addTextChangedListener(simpleTextWatcher);
        etPassword.addTextChangedListener(simpleTextWatcher);
        etVerifyCode.addTextChangedListener(simpleTextWatcher);
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
                updatePassword();
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
        map.put("type", Constant.UPDATE_PASSWORD_VERIFY_TYPE);
        presenter.sendVerifyCode(map);
        TimerUtil.getInstance().startTimer(tvSendCode);
    }

    private void updatePassword() {
        showLoading();
        String phoneNum = etPhoneNum.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        HashMap<String, Object> map = new HashMap<>();
        map.put("vp", phoneNum);
        map.put("code", verifyCode);
        map.put("newVpw", password);
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean != null) {
            map.put("token", userBean.getToken());
        }
        presenter.updatePassword(map);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TimerUtil.getInstance().cancelTimer();
    }

    @Override
    public void sendCodeSuccess(String msg) {

    }

    @Override
    public void sendCodeFail(String msg) {
        TimerUtil.getInstance().cancelTimer();
        tvSendCode.setClickable(true);
        tvSendCode.setText(R.string.getVerifyCode);
    }

    @Override
    public void updateSuccess(String msg) {
        dismissLoading();
        ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
        finish();
    }
}
