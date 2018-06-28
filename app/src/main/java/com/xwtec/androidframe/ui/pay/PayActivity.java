package com.xwtec.androidframe.ui.pay;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.SuperBaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.PAY_ROUTER)
public class PayActivity extends SuperBaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_zhifubao)
    LinearLayout llZFB;
    @BindView(R.id.ll_wx)
    LinearLayout llWX;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.price_view)
    PriceView priceView;

    private String totalMoney;

    @Override
    protected void init() {
        tvTitle.setText(R.string.pay);
        totalMoney = getIntent().getStringExtra("totalMoney");
        priceView.setPrice(totalMoney);
        llZFB.setSelected(true);
        btnPay.setText("支付宝支付￥" + totalMoney);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @OnClick({R.id.iv_left, R.id.ll_zhifubao, R.id.ll_wx, R.id.btn_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_zhifubao:
                llWX.setSelected(false);
                llZFB.setSelected(true);
                btnPay.setText("支付宝支付￥" + totalMoney);
                break;
            case R.id.ll_wx:
                llWX.setSelected(true);
                llZFB.setSelected(false);
                btnPay.setText("微信支付￥" + totalMoney);
                break;
            case R.id.btn_pay:

                break;
        }
    }
}
