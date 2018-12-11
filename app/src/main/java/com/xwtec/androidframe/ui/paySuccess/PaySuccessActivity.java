package com.xwtec.androidframe.ui.paySuccess;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.SuperBaseActivity;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.PAY_SUCCESS_ROUTER)
public class PaySuccessActivity extends SuperBaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_pay_money)
    TextView tvMoney;

    @Override
    protected void init() {
        tvTitle.setText("订单支付成功");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_success;
    }

    @OnClick({R.id.iv_left, R.id.tv_right, R.id.btn_go_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_right:
                finish();
                break;
            case R.id.btn_go_order:
                ARouter.getInstance().build(Constant.MY_ORDER_ROUTER)
                        .withInt(Constant.ORDER_STATUS, -1).navigation();
                finish();
                break;
            default:
                break;
        }
    }
}
