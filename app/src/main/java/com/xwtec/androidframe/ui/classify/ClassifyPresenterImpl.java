package com.xwtec.androidframe.ui.classify;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class ClassifyPresenterImpl extends BasePresenter<ClassifyContact.ClassifyView> implements ClassifyContact.ClassifyPresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public ClassifyPresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


}
