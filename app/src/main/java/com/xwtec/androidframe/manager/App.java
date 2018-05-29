package com.xwtec.androidframe.manager;

import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.xwtec.androidframe.di.component.AppComponent;
import com.xwtec.androidframe.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        AppComponent appComponent = DaggerAppComponent.builder().plus(this).build();
        appComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }
}
