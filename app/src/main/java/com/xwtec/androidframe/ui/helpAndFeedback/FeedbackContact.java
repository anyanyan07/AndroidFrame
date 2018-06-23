package com.xwtec.androidframe.ui.helpAndFeedback;

import com.xwtec.androidframe.base.BaseView;

import java.util.HashMap;

/**
 * Created by ayy on 2018/6/20.
 * Describe:xxx
 */

public interface FeedbackContact {
    interface FeedbackView extends BaseView {
        void feedbackSuccess(String msg);
    }

    interface FeedbackPresenter {
        void feedback(HashMap<String, Object> map);
    }
}
