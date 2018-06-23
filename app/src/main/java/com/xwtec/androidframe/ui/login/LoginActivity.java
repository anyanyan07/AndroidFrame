package com.xwtec.androidframe.ui.login;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.interfaces.SimpleTextWatcher;
import com.xwtec.androidframe.manager.Constant;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.tv_get_verify_code)
    TextView tvSendCode;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.login);
        ivLeft.setVisibility(View.VISIBLE);
        etPhoneNum.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                refreshBtnStatus();
            }
        });
        etVerifyCode.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                refreshBtnStatus();
            }
        });
    }

    private void refreshBtnStatus() {
        String phoneNum = etPhoneNum.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        if (phoneNum.length() == 11 && !TextUtils.isEmpty(verifyCode)) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
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
        map.put("type", Constant.LOGIN_VERIFY_TYPE);
        presenter.sendVerifyCode(map);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.tv_get_verify_code, R.id.tv_forget_password, R.id.tv_register, R.id.btn_login, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_verify_code:
                sendVerifyCode();
                break;
            case R.id.tv_forget_password:
                ARouter.getInstance().build(Constant.FORGET_PASSWORD_ROUTER).navigation();
                break;
            case R.id.tv_register:
                ARouter.getInstance().build(Constant.REGISTER_ROUTER).navigation();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.iv_left:
                finish();
                break;
            default:
                break;
        }
    }

    private void login() {
        String phoneNum = etPhoneNum.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        HashMap<String, Object> map = new HashMap<>();
        map.put("vp", phoneNum);
        map.put("vpwOrCode", verifyCode);
        presenter.login(map);
    }

    @Override
    public void sendCodeSuccess() {
        //开始倒计时
        tvSendCode.setClickable(false);
        final int count = 60;
        Observable.interval(1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        tvSendCode.setText(aLong + "s");
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvSendCode.setClickable(true);
                        tvSendCode.setText(R.string.getVerifyCode);
                    }

                    @Override
                    public void onComplete() {
                        tvSendCode.setClickable(true);
                        tvSendCode.setText(R.string.getVerifyCode);
                    }
                });
    }

    @Override
    public void sendCodeFail(String msg) {
        ToastUtils.showShort(msg);
        tvSendCode.setClickable(true);
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        ToastUtils.showShort("登录成功");
        CacheUtils.getInstance().put(Constant.USER_KEY,userBean);
        finish();
    }
}
