package com.xwtec.androidframe.ui.address;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreateAddressActivity extends BaseActivity<CreateAddPresenterImpl> implements CreateAddContact.CreateAddView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_consignee)
    EditText etConsignee;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_detail_add)
    EditText etDetailAdd;
    @BindView(R.id.rl_set_default)
    RelativeLayout rlSetDefault;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("新增收货地址");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.finish);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_address;
    }


    @OnClick({R.id.iv_left, R.id.tv_right, R.id.rl_set_default, R.id.ll_chose_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            //完成编辑
            case R.id.tv_right:
                createAddress();
                break;
            case R.id.rl_set_default:
                boolean selected = rlSetDefault.isSelected();
                rlSetDefault.setSelected(!selected);
                break;
            case R.id.ll_chose_address:

                break;
        }
    }

    private void createAddress() {
        String consignee = etConsignee.getText().toString().trim();
        if (TextUtils.isEmpty(consignee)) {
            ToastUtils.showShort("请填写收货人姓名");
            return;
        }
        String phoneNum = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }
        String address = tvAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showShort("请选择收货地址");
            return;
        }
        String detailAdd = etDetailAdd.getText().toString().trim();
        if (TextUtils.isEmpty(detailAdd)) {
            ToastUtils.showShort("请输入详细地址");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("receiver", consignee);
            jsonObject.put("receiveArea", address);
            jsonObject.put("detailsAddress", detailAdd);
            jsonObject.put("phone", phoneNum);
            jsonObject.put("isDefault", rlSetDefault.isSelected() ? 1 : 0);
            presenter.createAdd(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSuccess(String msg) {
        ToastUtils.showShort(msg);
        finish();
    }
}
