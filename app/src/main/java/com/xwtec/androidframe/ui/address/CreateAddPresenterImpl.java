package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public class CreateAddPresenterImpl extends BasePresenter<CreateAddContact.CreateAddView> implements CreateAddContact.CreateAddPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public CreateAddPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }
}
