package com.xwtec.androidframe.ui.helpAndFeedback;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.interfaces.SimpleTextWatcher;
import com.xwtec.androidframe.manager.Constant;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.FEEDBACK_ROUTER)
public class FeedbackActivity extends BaseActivity<FeedbackPresenterImpl> implements FeedbackContact.FeedbackView {
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.feedback);
        etPhoneNum.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                refreshBtnStatus();
            }
        });
        etContent.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                refreshBtnStatus();
            }
        });
    }

    private void refreshBtnStatus() {
        String phoneNum = etPhoneNum.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        if (phoneNum.length() == 11 && !TextUtils.isEmpty(content)) {
            btnSubmit.setEnabled(true);
        } else {
            btnSubmit.setEnabled(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @OnClick({R.id.btn_submit, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_submit:
                feedback();
                break;
            default:
                break;
        }
    }

    private void feedback() {
        String phoneNum = etPhoneNum.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phoneNum);
        map.put("content", content);
        presenter.feedback(map);
    }

    @Override
    public void feedbackSuccess(String msg) {
        ToastUtils.showShort(msg);
        finish();
    }
}
