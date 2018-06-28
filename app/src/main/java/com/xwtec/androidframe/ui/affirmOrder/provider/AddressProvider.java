package com.xwtec.androidframe.ui.affirmOrder.provider;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.MultiEntity;
import com.xwtec.androidframe.ui.address.AddressListActivity;
import com.xwtec.androidframe.ui.affirmOrder.AffirmAdapter;
import com.xwtec.androidframe.ui.affirmOrder.AffirmOrderActivity;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;

/**
 * Created by ayy on 2018/6/27.
 * Describe:xxx
 */
@ItemProviderTag(viewType = AffirmAdapter.ADDRESS_TYPE, layout = R.layout.address_header)
public class AddressProvider extends BaseItemProvider<MultiEntity<AffirmResponse.ReceiveAddressBean>, BaseViewHolder> {
    private AffirmOrderActivity affirmOrderActivity;

    public AddressProvider(AffirmOrderActivity affirmOrderActivity) {
        this.affirmOrderActivity = affirmOrderActivity;
    }

    @Override
    public void convert(BaseViewHolder helper, MultiEntity<AffirmResponse.ReceiveAddressBean> data, int position) {
        AffirmResponse.ReceiveAddressBean address = data.getData();
        //没有收货地址
        if (address == null) {
            helper.setVisible(R.id.ll_add_receiver, true);
            helper.getView(R.id.ll_address).setVisibility(View.GONE);
            return;
        }
        helper.setVisible(R.id.ll_address, true);
        helper.getView(R.id.ll_add_receiver).setVisibility(View.GONE);
        helper.setText(R.id.tv_name, address.getReceiver());
        helper.setText(R.id.tv_phone_num, address.getPhone());
        helper.setText(R.id.tv_address, address.getReceiveArea());
        helper.setText(R.id.tv_detail_address, address.getDetailsAddress());
    }

    @Override
    public void onClick(BaseViewHolder helper, MultiEntity<AffirmResponse.ReceiveAddressBean> data, int position) {
        //跳转到收货地址列表
        ARouter.getInstance().build(Constant.ADDRESS_ROUTER)
                .withString("type", AddressListActivity.CHOSE_TYPE).navigation(affirmOrderActivity, AffirmOrderActivity.ADDRESS_LIST_REQ_CODE);
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultiEntity<AffirmResponse.ReceiveAddressBean> data, int position) {
        return false;
    }
}
