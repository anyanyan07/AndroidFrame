package com.xwtec.androidframe.util;

import android.widget.TextView;

import com.xwtec.androidframe.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ayy on 2018/6/24.
 * Describe:xxx
 */

public class TimerUtil {
    private static TimerUtil timerUtil;
    private Observable<Long> timerObservable;
    private CompositeDisposable compositeDisposable;

    public static TimerUtil getInstance() {
        if (timerUtil == null) {
            synchronized (TimerUtil.class) {
                if (timerUtil == null) {
                    timerUtil = new TimerUtil();
                }
            }
        }
        return timerUtil;
    }

    public void startTimer(final TextView tvSendCode) {
        compositeDisposable = new CompositeDisposable();
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


    public void cancelTimer() {
        if (timerObservable != null) {
            timerObservable.unsubscribeOn(Schedulers.io());
            timerObservable = null;
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

}
