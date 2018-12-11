package com.xwtec.androidframe.di.module;


import com.xwtec.androidframe.di.scope.ActivityScope;
import com.xwtec.androidframe.di.scope.FragmentScope;
import com.xwtec.androidframe.ui.address.AddressListActivity;
import com.xwtec.androidframe.ui.address.CreateAddressActivity;
import com.xwtec.androidframe.ui.affirmOrder.AffirmOrderActivity;
import com.xwtec.androidframe.ui.classify.ClassifyFragment;
import com.xwtec.androidframe.ui.classify.ClassifyListActivity;
import com.xwtec.androidframe.ui.comment.CommentActivity;
import com.xwtec.androidframe.ui.comments.CommentsActivity;
import com.xwtec.androidframe.ui.express.ExpressActivity;
import com.xwtec.androidframe.ui.expressInfo.ExpressInfoActivity;
import com.xwtec.androidframe.ui.forgetPassword.ForgetPasswordActivity;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailActivity;
import com.xwtec.androidframe.ui.helpAndFeedback.ContactServiceActivity;
import com.xwtec.androidframe.ui.helpAndFeedback.FeedbackActivity;
import com.xwtec.androidframe.ui.helpAndFeedback.HelpActivity;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.login.LoginActivity;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.ui.mine.MineFragment;
import com.xwtec.androidframe.ui.moneyReturn.MoneyReturnActivity;
import com.xwtec.androidframe.ui.myOrders.MyOrderActivity;
import com.xwtec.androidframe.ui.orderDetail.OrderDetailActivity;
import com.xwtec.androidframe.ui.pay.PayActivity;
import com.xwtec.androidframe.ui.paySuccess.PaySuccessActivity;
import com.xwtec.androidframe.ui.personalInfo.PersonalInfoActivity;
import com.xwtec.androidframe.ui.refundDetail.RefundActivity;
import com.xwtec.androidframe.ui.register.RegisterActivity;
import com.xwtec.androidframe.ui.saleReturn.SaleReturnActivity;
import com.xwtec.androidframe.ui.setting.SettingActivity;
import com.xwtec.androidframe.ui.shopCart.ShopCartFragment;
import com.xwtec.androidframe.ui.splash.SplashActivity;
import com.xwtec.androidframe.ui.test.TestActivity;
import com.xwtec.androidframe.ui.updatePassword.UpdatePasswordActivity;

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
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @FragmentScope
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract ClassifyFragment classifyFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract ShopCartFragment shopCartFragment();

    @FragmentScope
    @ContributesAndroidInjector
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

    @ActivityScope
    @ContributesAndroidInjector
    abstract PersonalInfoActivity personalInfoActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SettingActivity settingActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract UpdatePasswordActivity updatePasswordActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddressListActivity addressListActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract CreateAddressActivity createAddressActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MyOrderActivity myOrderActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ClassifyListActivity classifyListActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AffirmOrderActivity affirmOrderActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract PayActivity payActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract OrderDetailActivity orderDetailActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract RefundActivity refundActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract SaleReturnActivity saleReturnActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ExpressActivity expressActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract ExpressInfoActivity expressInfoActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MoneyReturnActivity moneyReturnActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract CommentActivity commentActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract CommentsActivity commentsActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract PaySuccessActivity paySuccessActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract TestActivity testActivity();

}
