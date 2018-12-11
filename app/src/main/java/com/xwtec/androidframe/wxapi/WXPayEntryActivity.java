package com.xwtec.androidframe.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.manager.App;
import com.xwtec.androidframe.manager.AppManager;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.pay.PayActivity;

/**
 * 微信支付结果回调
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = ((App) getApplication()).getWxApi();
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    ToastUtils.showShort("微信支付成功");
                    ARouter.getInstance().build(Constant.PAY_SUCCESS_ROUTER)
                            .navigation();
                    AppManager.getInstance().finishActivity(PayActivity.class);
                    finish();
                    break;
                default:
                    ToastUtils.showShort("微信支付失败,请重新支付");
                    finish();
                    break;
            }
        }
    }
}