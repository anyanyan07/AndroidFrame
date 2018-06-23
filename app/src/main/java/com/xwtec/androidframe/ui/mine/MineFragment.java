package com.xwtec.androidframe.ui.mine;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.tv_username)
    TextView tvUsername;

    private boolean in = true;
    private boolean first = true;
    private UserBean userBean;

    @Override
    public void onResume() {
        super.onResume();
        userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean == null) {
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
            userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
            if (userBean == null) {
                if (in) {
                    in = false;
                    ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
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
        initRxBus();
    }

    private void initRxBus() {
        RxBus.getInstance().register(RxBusMSG.class, new Consumer<RxBusMSG>() {
            @Override
            public void accept(RxBusMSG rxBusMSG) throws Exception {
                switch (rxBusMSG.getCode()) {
                    case Constant.RX_LOGIN_SUCCESS:
                        userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
                        ImageLoadUtil.loadCenterCrop(context, userBean.getImgHead(), ivUserHeader);
                        tvUsername.setText(userBean.getNickName());
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.iv_user_header, R.id.iv_look_more_order, R.id.tv_look_more, R.id.ll_obligation,
            R.id.ll_wait_send, R.id.ll_wait_receive, R.id.ll_had_cancel, R.id.ll_had_finished,
            R.id.ll_online_service, R.id.ll_feedback, R.id.ll_setting, R.id.iv_user_more, R.id.tv_username})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                break;
            case R.id.iv_user_more:
            case R.id.tv_username:
                ARouter.getInstance().build(Constant.PERSONAL_ROUTER).navigation();
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
                ARouter.getInstance().build(Constant.SETTING_ROUTER).navigation();
                break;
            default:
                break;
        }
    }
}
