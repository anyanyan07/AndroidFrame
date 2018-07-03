package com.xwtec.androidframe.ui.address;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.address.bean.Address;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;
import com.xwtec.androidframe.util.SpacesItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constant.ADDRESS_ROUTER)
public class AddressListActivity extends BaseActivity<AddressPresenterImpl> implements AddressContact.AddressView {

    private static final int CODE_CREATE = 0x01;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;

    private BaseQuickAdapter<Address, BaseViewHolder> adapter;
    public static final String CHOSE_TYPE = "CHOSE_TYPE";
    private static final String MANAGER_TYPE = "MANAGER_TYPE";
    private String type = MANAGER_TYPE;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void init() {
        super.init();
        type = getIntent().getStringExtra("type");
        if (CHOSE_TYPE.equals(type)) {
            tvTitle.setText("选择收货地址");
        } else {
            tvTitle.setText(R.string.managerAddress);
        }
        initRxBus();
        initRv();
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        presenter.queryAddress(userBean.getToken());
    }

    private void initRxBus() {
        RxBus.getInstance().register(RxBusMSG.class, new Consumer<RxBusMSG>() {
            @Override
            public void accept(RxBusMSG rxBusMSG) throws Exception {
                switch (rxBusMSG.getCode()) {
                    case Constant.RX_ADDRESS_REFRESH:
                        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
                        presenter.queryAddress(userBean.getToken());
                        break;
                }
            }
        });
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new SpacesItemDecoration("bottom", ConvertUtils.dp2px(10)));
        adapter = new BaseQuickAdapter<Address, BaseViewHolder>(R.layout.address_item) {
            @Override
            protected void convert(final BaseViewHolder helper, final Address address) {
                helper.setText(R.id.tv_name, address.getReceiver());
                helper.setText(R.id.tv_phone_num, address.getPhone());
                helper.setText(R.id.tv_address, address.getReceiveArea());
                helper.setText(R.id.tv_detail_address, address.getDetailsAddress());
                helper.getView(R.id.ll_default_add).setSelected(1 == address.getIsDefault());
                if (CHOSE_TYPE.equals(type)) {
                    helper.getView(R.id.ll_manager).setVisibility(View.GONE);
                }
                helper.getView(R.id.ll_del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = address.getId();
                        delAdd(id + "", helper.getAdapterPosition());
                    }
                });
                helper.getView(R.id.ll_edit_address).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.CREATE_ADDRESS_ROUTER)
                                .withSerializable("address", address)
                                .navigation();
                    }
                });
                helper.getView(R.id.ll_address_info).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CHOSE_TYPE.equals(type)) {
                            Intent intent = new Intent();
                            intent.putExtra("address", address);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            ARouter.getInstance().build(Constant.CREATE_ADDRESS_ROUTER)
                                    .withSerializable("address", address)
                                    .navigation();
                        }
                    }
                });
                helper.getView(R.id.ll_default_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用修改地址接口
                        if (1 == address.getIsDefault()) {
                            return;
                        }
                        address.setIsDefault(1);
                        updateAddress(address);
                    }
                });
            }
        };
        rv.setAdapter(adapter);
    }

    private void updateAddress(Address address) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", address.getId());
            jsonObject.put("receiver", address.getReceiver());
            jsonObject.put("receiveArea", address.getReceiveArea());
            jsonObject.put("detailsAddress", address.getDetailsAddress());
            jsonObject.put("phone", address.getPhone());
            jsonObject.put("isDefault", address.getIsDefault());
            jsonObject.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
            presenter.updateAddress(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void delAdd(String id, int position) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ids", id);
        map.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
        presenter.delAdd(map, position);
    }

    @Override
    public void delSuccess(int position) {
        presenter.queryAddress(((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
    }

    @Override
    public void updateSuccess(String msg) {
        presenter.queryAddress(((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
    }

    @Override
    public void querySuccess(List<Address> addressList) {
        adapter.setNewData(addressList);
    }


    @OnClick({R.id.iv_left, R.id.btn_create_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_create_address:
                ARouter.getInstance().build(Constant.CREATE_ADDRESS_ROUTER).navigation(this, CODE_CREATE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CREATE:
                    UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
                    presenter.queryAddress(userBean.getToken());
                    break;
            }
        }
    }
}
