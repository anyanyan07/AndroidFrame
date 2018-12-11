package com.xwtec.androidframe.ui.pay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.App;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constant.PAY_ROUTER)
public class PayActivity extends BaseActivity<PayPresenterImpl> implements PayContact.PayView {

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
    private int payWay = 0;
    private long addressId;
    private List<String> orderNumbers;
    private String discountCode;
    private static final int ALIPAY_RESULT = 0;

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        if (intent != null) {
            addressId = intent.getLongExtra("addressId", -1);
            orderNumbers = intent.getStringArrayListExtra("orderNumbers");
            discountCode = intent.getStringExtra("discountCode");
        }
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
                payWay = 0;
                break;
            case R.id.ll_wx:
                llWX.setSelected(true);
                llZFB.setSelected(false);
                btnPay.setText("微信支付￥" + totalMoney);
                payWay = 1;
                break;
            case R.id.btn_pay:
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("addressId", addressId);
                    JSONArray jsonArray = new JSONArray(orderNumbers);
                    jsonObject.put("orderNumbers", jsonArray);
                    jsonObject.put("payWay", payWay);
                    jsonObject.put("discountCode", discountCode);
                    jsonObject.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
                    presenter.pay(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void paySuccess(PayBean payBean) {
        if (payWay == 1) {
            invokeWXPay(payBean);
        } else if (payWay == 0) {
            invokeAliPay(payBean);
        }
    }


    /**
     * 拉起微信支付
     */
    private void invokeWXPay(PayBean payBean) {
        PayReq req = new PayReq();
        req.appId = payBean.getAppId();
        req.partnerId = payBean.getPartnerId();
        req.prepayId = payBean.getPrepayId();
        req.nonceStr = payBean.getNonceStr();
        req.timeStamp = payBean.getTimeStamp();
        req.packageValue = payBean.getPackageStr();
        req.sign = payBean.getSign();
        ((App) getApplication()).getWxApi().sendReq(req);
    }

    /**
     * 拉起支付宝支付
     */
    private void invokeAliPay(PayBean payBean) {
        final String data = payBean.getData();
        //必须在子线程中调用
        new Thread() {
            @Override
            public void run() {
                super.run();
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(data, true);
                Message message = handler.obtainMessage();
                message.what = ALIPAY_RESULT;
                message.obj = result;
                handler.sendMessage(message);
            }
        }.start();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ALIPAY_RESULT:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showShort("支付成功");
                        ARouter.getInstance().build(Constant.PAY_SUCCESS_ROUTER)
                                .navigation();
                        finish();
                    } else {
                        ToastUtils.showShort("支付失败,请重新支付");
                    }
                    break;
                default:
                    break;
            }
        }
    };

}
