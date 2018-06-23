package com.xwtec.androidframe.ui.updatePassword;

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

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.updatePassword);
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
    }

    private void updatePassword() {
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
        HashMap<String, Object> map = new HashMap<>();
        map.put("vp", phoneNum);
        map.put("code", verifyCode);
        map.put("newVpw", passowrd);
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean != null) {
            map.put("token", userBean.getToken());
        }
        presenter.updatePassword(map);
    }

    @Override
    public void updateSuccess(String msg) {
        ToastUtils.showShort(msg);
        finish();
    }


    private Observable<Long> timerObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void sendCodeSuccess(String msg) {
        ToastUtils.showShort(R.string.sendVerifyCodeSuccess);
        tvSendCode.setClickable(false);
        final int count = 60;
        timerObservable = Observable.interval(1, TimeUnit.SECONDS)
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
                        compositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

        timerObservable.subscribe(new Observer<Long>() {
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
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerObservable != null) {
            timerObservable.unsubscribeOn(Schedulers.io());
            timerObservable = null;
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
