package com.xwtec.androidframe.ui.saleReturn;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constant.SALE_RETURN_ROUTER)
public class SaleReturnActivity extends BaseActivity<SaleReturnPresenterImpl> implements SaleReturnContact.SaleReturnView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.good_price)
    PriceView goodPrice;
    @BindView(R.id.tv_good_unit_num)
    TextView tvGoodUnitNum;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.et_express_num)
    EditText etExpressNum;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_case)
    EditText etCase;

    private static final int EXPRESS_REQ_CODE = 1;
    private int expressId = -1;
    private long orderId = -1;
    private int position;

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        if (intent != null) {
            Order order = ((Order) intent.getSerializableExtra("order"));
            orderId = order.getOrderId();
            ImageLoadUtil.loadFitCenter(this, order.getImgUrl(), ivGood);
            tvGoodName.setText(order.getTitle() + order.getIntroduction());
            goodPrice.setPrice(order.getUnitPrice());
            tvGoodUnitNum.setText("x" + order.getGoodsNumber());
            position = intent.getIntExtra("position", -1);
        }
        tvTitle.setText("退货");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_return;
    }

    @OnClick({R.id.iv_left, R.id.ll_express, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_express:
                ARouter.getInstance().build(Constant.EXPRESS_ROUTER).navigation(this, EXPRESS_REQ_CODE);
                break;
            case R.id.btn_submit:
                saleReturn();
                break;
            default:
                break;
        }
    }

    private void saleReturn() {
        if (expressId == -1) {
            ToastUtils.showShort("请选择物流公司");
            return;
        }
        String expressNum = etExpressNum.getText().toString().trim();
        if (TextUtils.isEmpty(expressNum)) {
            ToastUtils.showShort("请填写物流单号");
            return;
        }
        String phoneNum = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请填写联系人手机号");
            return;
        }
        String returnCase = etCase.getText().toString().trim();
        if (TextUtils.isEmpty(returnCase)) {
            ToastUtils.showShort("请填写退货原因");
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId", orderId);
            jsonObject.put("expressId", expressId);
            jsonObject.put("expressNumber", expressNum);
            jsonObject.put("cause", returnCase);
            jsonObject.put("imgUrl", "");
            jsonObject.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
            presenter.saleReturn(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case EXPRESS_REQ_CODE:
                    if (data != null) {
                        expressId = data.getIntExtra("expressId", -1);
                        String expressName = data.getStringExtra("expressName");
                        tvExpress.setText(expressName);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void saleReturnSuccess() {
        ToastUtils.showShort("退货中");
        int[] data = {position, Constant.SALE_RETURNING};
        setResult(RESULT_OK);
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ORDER_CHANGE, data));
        finish();
    }
}
