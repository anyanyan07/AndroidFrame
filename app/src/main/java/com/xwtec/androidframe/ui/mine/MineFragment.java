package com.xwtec.androidframe.ui.mine;


import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

import java.io.File;

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
    private UserBean userBean;

    private String filePath = "";

    @Inject
    public MineFragment() {
    }

    @Override
    protected void init() {
        ivLeft.setVisibility(View.GONE);
        tvTitle.setText("我的");
        if (TextUtils.isEmpty(filePath)) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            }
        }
        refreshUserInfo();
        initRxBus();
    }

    private void initRxBus() {
        RxBus.getInstance().register(RxBusMSG.class, new Consumer<RxBusMSG>() {
            @Override
            public void accept(RxBusMSG rxBusMSG) throws Exception {
                switch (rxBusMSG.getCode()) {
                    case Constant.RX_LOGIN_SUCCESS:
                    case Constant.RX_USER_INFO:
                        refreshUserInfo();
                        break;
                    case Constant.RX_LOGOUT:
                        ((MainActivity) getActivity()).goBackTab();
                        break;
                }
            }
        });
    }

    private void refreshUserInfo() {
        userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (!TextUtils.isEmpty(userBean.getImgHead())) {
            ImageLoadUtil.loadCenterCrop(context, userBean.getImgHead(), ivUserHeader);
        } else {
            File file = new File(filePath + "/header/" + userBean.getUserId() + ".jpg");
            if (file.exists()) {
                ImageLoadUtil.loadCircleImageFromFile(context, file, ivUserHeader);
            }
        }
        tvUsername.setText(userBean.getNickName());
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
            //全部订单
            case R.id.iv_look_more_order:
            case R.id.tv_look_more:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, -1).navigation();
                break;
            //待付款
            case R.id.ll_obligation:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, Constant.WAIT_PAY).navigation();
                break;
            //待发货
            case R.id.ll_wait_send:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, Constant.PAIED_WAIT_SEND).navigation();
                break;
            //待收货
            case R.id.ll_wait_receive:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, Constant.SENDED).navigation();
                break;
            //已取消
            case R.id.ll_had_cancel:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, Constant.CANCELED).navigation();
                break;
            case R.id.ll_had_finished:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, Constant.FINISHED).navigation();
                break;
            //在线客服
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
