package com.xwtec.androidframe.ui.helpAndFeedback;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

/**
 * Created by ayy on 2018/6/20.
 * Describe:xxx
 */

public class FeedbackPresenterImpl extends BasePresenter<FeedbackContact.FeedbackView> implements FeedbackContact.FeedbackPresenter {

    private NetResourceRepo netResourceRepo;

    @Inject
    public FeedbackPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }
}
