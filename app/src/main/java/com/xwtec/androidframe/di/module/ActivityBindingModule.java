package com.xwtec.androidframe.di.module;


import com.xwtec.androidframe.di.scope.ActivityScope;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Copyright (c)2017
 * 欣网互联网络科技有限公司
 * author: lpc
 * created on: 2018/3/7 0007
 * description:
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivity();
}
