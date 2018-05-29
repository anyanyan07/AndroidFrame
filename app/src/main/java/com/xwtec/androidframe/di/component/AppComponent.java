package com.xwtec.androidframe.di.component;


import com.xwtec.androidframe.di.module.ActivityBindingModule;
import com.xwtec.androidframe.di.module.AppModule;
import com.xwtec.androidframe.manager.App;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * author: ayy
 * created on: 2018/4/20 0020
 * description:全局module
 */
@Singleton
@Component(modules = {AppModule.class,
        ActivityBindingModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    NetResourceRepo getNetResourceRepo();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder plus(App app);

        AppComponent build();
    }
}
