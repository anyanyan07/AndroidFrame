package com.xwtec.androidframe.di.module;


import com.xwtec.androidframe.di.scope.ActivityScope;
import com.xwtec.androidframe.di.scope.FragmentScope;
import com.xwtec.androidframe.ui.classify.ClassifyFragment;
import com.xwtec.androidframe.ui.forgetPassword.ForgetPasswordActivity;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailActivity;
import com.xwtec.androidframe.ui.helpAndFeedback.ContactServiceActivity;
import com.xwtec.androidframe.ui.helpAndFeedback.FeedbackActivity;
import com.xwtec.androidframe.ui.helpAndFeedback.HelpActivity;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.login.LoginActivity;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.ui.mine.MineFragment;
import com.xwtec.androidframe.ui.register.RegisterActivity;
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

    @ActivityScope
    @ContributesAndroidInjector
    abstract RegisterActivity registerActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract GoodDetailActivity goodDetailActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ForgetPasswordActivity forgetPasswordActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract HelpActivity helpActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract FeedbackActivity feedbackActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ContactServiceActivity contactServiceActivity();
}
