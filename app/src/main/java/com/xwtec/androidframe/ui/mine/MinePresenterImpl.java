package com.xwtec.androidframe.ui.mine;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public class MinePresenterImpl extends BasePresenter<MineContact.MineView> implements MineContact.MinePresenter {
    private NetResourceRepo mNetResourceRepo;

    @Inject
    public MinePresenterImpl(NetResourceRepo netResourceRepo) {
        this.mNetResourceRepo = netResourceRepo;
    }


}
