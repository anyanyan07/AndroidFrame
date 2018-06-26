package com.xwtec.androidframe.ui.address;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.ADDRESS_ROUTER)
public class AddressListActivity extends BaseActivity<AddressPresenterImpl> implements AddressContact.AddressView {

    private static final int CODE_CREATE = 0x01;
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
            protected void convert(BaseViewHolder helper, final Address address) {
                helper.setText(R.id.tv_name, address.getReceiver());
                helper.setText(R.id.tv_phone_num, address.getPhone());
                helper.setText(R.id.tv_address, address.getReceiveArea());
                helper.setText(R.id.tv_detail_address, address.getDetailsAddress());
            }
        };
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_del:
                        int id = ((Address) adapter.getItem(position)).getId();
                        delAdd(id+"",position);
                        break;
                }
            }
        });
        rv.setAdapter(adapter);
    }

    private void delAdd(String id,int position){
        HashMap<String,Object> map = new HashMap<>();
        map.put("ids",id);
        map.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
        presenter.delAdd(map,position);
    }

    @Override
    public void delSuccess(int position) {
        adapter.remove(position);
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
