package com.xwtec.androidframe.ui.splash;

import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity<SplashPresenterImpl> {

    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.price_view)
    PriceView priceView;

    @Override
    protected void init() {
        priceView.setPrice("33.99");
        final int count = 5;
        Observable.interval(1, TimeUnit.SECONDS)
                .take(6)
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
                        tvCount.setText(aLong + "s");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ToastUtils.showShort("倒计时结束");
                        ARouter.getInstance().build(Constant.MAIN_ROUTER).navigation();
                        finish();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
