package com.xwtec.androidframe.ui.helpAndFeedback;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.SuperBaseActivity;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.HELP_ROUTER)
public class HelpActivity extends SuperBaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_left)
    ImageView ivLeft;

    @Override
    protected void init() {
        tvTitle.setText(R.string.helpAndFeedback);
        ivLeft.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @OnClick({R.id.iv_left, R.id.ll_feedback, R.id.ll_contact_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_feedback:
                ARouter.getInstance().build(Constant.FEEDBACK_ROUTER).navigation();
                break;
            case R.id.ll_contact_service:
                ARouter.getInstance().build(Constant.CONTACT_SERVICE_ROUTER).navigation();
                break;
        }
    }
}
