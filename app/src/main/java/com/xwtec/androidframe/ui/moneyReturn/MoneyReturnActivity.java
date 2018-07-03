package com.xwtec.androidframe.ui.moneyReturn;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.MONEY_RETURN_ROUTER)
public class MoneyReturnActivity extends BaseActivity<MoneyReturnPresenterImpl> implements MoneyReturnContact.MoneyReturnView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.et_case)
    EditText etCase;

    private long orderId;
    private int position;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("退款");
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getLongExtra("orderId", -1);
            position = intent.getIntExtra("position", -1);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_money_return;
    }

    @OnClick({R.id.iv_left, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_submit:
                moneyReturn();
                break;
        }
    }

    private void moneyReturn() {
        String caseStr = etCase.getText().toString().trim();
        if (TextUtils.isEmpty(caseStr)) {
            ToastUtils.showShort("请输入退款原因");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("cause", caseStr);
        map.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
        presenter.moneyReturn(map);
    }

    @Override
    public void moneyReturnSuccess() {
        ToastUtils.showShort("退款中");
        int[] data = {position, Constant.REFUNDING};
        setResult(RESULT_OK);
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ORDER_CHANGE, data));
        finish();
    }
}
