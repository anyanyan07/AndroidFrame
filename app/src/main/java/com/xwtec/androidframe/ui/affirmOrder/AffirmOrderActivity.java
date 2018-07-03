package com.xwtec.androidframe.ui.affirmOrder;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.MultiEntity;
import com.xwtec.androidframe.ui.address.bean.Address;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.ui.affirmOrder.bean.SubmitOrderBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 确认订单页面
 */
@Route(path = Constant.AFFIRM_ORDER_ROUTER)
public class AffirmOrderActivity extends BaseActivity<AffirmOrderPresenterImpl> implements AffirmOrderContact.AffirmOrderView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.total_price)
    PriceView totalPrice;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    public static final int ADDRESS_LIST_REQ_CODE = 0x01;
    private AffirmAdapter adapter;
    private List<MultiEntity> data = new ArrayList<>();
    private MultiEntity<AffirmResponse.ReceiveAddressBean> addressMultiEntity;
    private MultiEntity<AffirmResponse.OrderGoodsBean> contentMultiEntity;

    private String json;
    private int receiverId = -1;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.affirmOrder);
        initRv();
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        presenter.affirmOrder(RequestBody.create(MediaType.parse("application/json"), json));
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        addressMultiEntity = new MultiEntity<>(AffirmAdapter.ADDRESS_TYPE);
        contentMultiEntity = new MultiEntity<>(AffirmAdapter.GOODS_TYPE);
        data.add(addressMultiEntity);
        data.add(contentMultiEntity);
        adapter = new AffirmAdapter(data, this);
        rv.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_affirm_order;
    }

    @OnClick({R.id.iv_left, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_submit:
                submitOrder();
                break;
        }
    }

    private void submitOrder() {
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject.put("receiverId", receiverId);
            presenter.submitOrder(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADDRESS_LIST_REQ_CODE:
                    if (data != null) {
                        Address address = ((Address) data.getSerializableExtra("address"));
                        AffirmResponse.ReceiveAddressBean receiver = new AffirmResponse.ReceiveAddressBean();
                        receiverId = address.getId();
                        receiver.setId(address.getId());
                        receiver.setDetailsAddress(address.getDetailsAddress());
                        receiver.setPhone(address.getPhone());
                        receiver.setReceiver(address.getReceiver());
                        receiver.setReceiveArea(address.getReceiveArea());
                        addressMultiEntity.setData(receiver);
                        adapter.updateAddress();
                        btnSubmit.setEnabled(true);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void affirmSuccess(AffirmResponse affirmResponse) {
        addressMultiEntity.setData(affirmResponse.getReceiveAddress());
        contentMultiEntity.setDataList(affirmResponse.getOrderGoods());
        if (affirmResponse.getReceiveAddress() != null) {
            btnSubmit.setEnabled(true);
            receiverId = affirmResponse.getReceiveAddress().getId();
        }
        totalPrice.setPrice(affirmResponse.getTotalPrice());
        adapter.notifyDataSetChanged();
    }

    //提交订单成功，跳转到支付页面
    @Override
    public void submitSuccess(SubmitOrderBean submitOrderBean) {
        ARouter.getInstance().build(Constant.PAY_ROUTER)
                .withString("totalMoney", submitOrderBean.getTotalPrice()).navigation();
        finish();
    }
}
