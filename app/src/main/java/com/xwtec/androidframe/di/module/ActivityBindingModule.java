package com.xwtec.androidframe.di.module;


import com.xwtec.androidframe.di.scope.ActivityScope;
import com.xwtec.androidframe.di.scope.FragmentScope;
import com.xwtec.androidframe.ui.classify.ClassifyFragment;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.ui.mine.MineFragment;
import com.xwtec.androidframe.ui.shopCart.ShopCartFragment;
import com.xwtec.androidframe.ui.splash.SplashActivity;

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

    @ActivityScope
    @ContributesAndroidInjector(modules = {SplashModule.class})
    abstract SplashActivity splashActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = {HomeModule.class})
    abstract HomeFragment homeFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {ClassifyModule.class})
    abstract ClassifyFragment classifyFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {ShopCartModule.class})
    abstract ShopCartFragment shopCartFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {MineModule.class})
    abstract MineFragment mineFragment();
}
