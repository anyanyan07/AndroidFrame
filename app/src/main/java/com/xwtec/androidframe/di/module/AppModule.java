package com.xwtec.androidframe.di.module;


import android.content.Context;

import com.xwtec.androidframe.di.qualifier.CommonApiQualifier;
import com.xwtec.androidframe.manager.App;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.manager.net.NetResourceRepoSupport;
import com.xwtec.androidframe.manager.net.Service;
import com.xwtec.androidframe.manager.intercepter.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ayy
 * 全局提供的类
 */

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    static OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @CommonApiQualifier
    static Retrofit provideRetrofit(Retrofit.Builder builder, @CommonApiQualifier OkHttpClient client) {
        return builder
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    @CommonApiQualifier
    static OkHttpClient provideClient(OkHttpClient.Builder builder, App context) {
        builder.addInterceptor(new HeaderInterceptor(context))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    @Singleton
    @Provides
    static Service provideHbCmccService(@CommonApiQualifier Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

    @Binds
    abstract Context providerContext(App context);

    @Singleton
    @Binds
    abstract NetResourceRepo bindNetResourceRepo(NetResourceRepoSupport netResourceRepoSupport);

}
