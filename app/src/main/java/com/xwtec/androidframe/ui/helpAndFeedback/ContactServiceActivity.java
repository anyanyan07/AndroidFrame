package com.xwtec.androidframe.ui.helpAndFeedback;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.SuperBaseActivity;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;
@Route(path = Constant.CONTACT_SERVICE_ROUTER)
public class ContactServiceActivity extends SuperBaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void init() {
        tvTitle.setText(R.string.helpAndFeedback);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_service;
    }

    @OnClick(R.id.iv_left)
    public void onClick() {
        finish();
    }
}
