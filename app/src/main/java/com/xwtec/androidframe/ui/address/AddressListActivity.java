package com.xwtec.androidframe.ui.address;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.ADDRESS_ROUTER)
public class AddressListActivity extends BaseActivity<AddressPresenterImpl> implements AddressContact.AddressView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;

    private BaseQuickAdapter<Address, BaseViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.managerAddress);
        initRv();
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        presenter.queryAddress(userBean.getToken());
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter<Address, BaseViewHolder>(R.layout.address_item) {
            @Override
            protected void convert(BaseViewHolder helper, Address address) {
                helper.setText(R.id.tv_name, address.getReceiver());
                helper.setText(R.id.tv_phone_num, address.getPhone());
                helper.setText(R.id.tv_address, address.getReceiveArea());
                helper.setText(R.id.tv_detail_address, address.getDetailsAddress());
            }
        };
        rv.setAdapter(adapter);
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
                break;
        }
    }
}
