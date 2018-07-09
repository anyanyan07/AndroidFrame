package com.xwtec.androidframe.ui.helpAndFeedback;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.HashMap;

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

    @Override
    public void feedback(HashMap<String, Object> map) {
        netResourceRepo.feedback(map)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            view.feedbackSuccess(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
