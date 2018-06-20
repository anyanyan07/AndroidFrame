package com.xwtec.androidframe.ui.mine;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<MinePresenterImpl> implements MineContact.MineView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;
    @BindView(R.id.iv_left)
    ImageView ivLeft;

    private boolean in = true;
    private boolean first = true;

    @Override
    public void onResume() {
        super.onResume();
        if (true) {
            if (in && first) {
                in = false;
                ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation(getActivity(), 0);
                return;
            }
            if (!in) {
                in = true;
                ((MainActivity) getActivity()).goBackTab();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (true) {
                if (in) {
                    in = false;
                    ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation(getActivity(), 0);
                }
            }
        }
    }

    @Inject
    public MineFragment() {
    }

    @Override
    protected void init() {
        ivLeft.setVisibility(View.GONE);
        tvTitle.setText("我的");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.iv_user_header, R.id.iv_look_more_order, R.id.tv_look_more, R.id.ll_obligation,
            R.id.ll_wait_send, R.id.ll_wait_receive, R.id.ll_had_cancel, R.id.ll_had_finished,
            R.id.ll_online_service, R.id.ll_feedback, R.id.ll_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                break;
            case R.id.iv_look_more_order:
            case R.id.tv_look_more:
                break;
            case R.id.ll_obligation:
                break;
            case R.id.ll_wait_send:
                break;
            case R.id.ll_wait_receive:
                break;
            case R.id.ll_had_cancel:
                break;
            case R.id.ll_had_finished:
                break;
            case R.id.ll_online_service:
                break;
            case R.id.ll_feedback:
                ARouter.getInstance().build(Constant.HELP_ROUTER).navigation();
                break;
            case R.id.ll_setting:
                break;
            default:
                break;
        }
    }
}
