package com.xwtec.androidframe.ui.affirmOrder;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.xwtec.androidframe.ui.MultiEntity;
import com.xwtec.androidframe.ui.affirmOrder.provider.AddressProvider;
import com.xwtec.androidframe.ui.affirmOrder.provider.ContentProvider;
import com.xwtec.androidframe.ui.affirmOrder.provider.DiscountCodeProvider;

import java.util.List;

/**
 * Created by ayy on 2018/6/27.
 * Describe:xxx
 */

public class AffirmAdapter extends MultipleItemRvAdapter<MultiEntity, BaseViewHolder> {
    public static final int ADDRESS_TYPE = 0x01;
    public static final int GOODS_TYPE = 0x02;
    public static final int DISCOUNT_TYPE = 0x03;

    private AddressProvider addressProvider;
    private DiscountCodeProvider discountCodeProvider;

    AffirmAdapter(@Nullable List<MultiEntity> data, AffirmOrderActivity affirmOrderActivity) {
        super(data);
        addressProvider = new AddressProvider(affirmOrderActivity);
//        discountCodeProvider = new DiscountCodeProvider();
        finishInitialize();
    }

    @Override
    protected int getViewType(MultiEntity multiEntity) {
        return multiEntity.getType();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(addressProvider);
        mProviderDelegate.registerProvider(new ContentProvider());
//        mProviderDelegate.registerProvider(discountCodeProvider);
    }

    public void updateAddress() {
        notifyItemChanged(0);
    }

    public void updateContent(){
        notifyItemChanged(1);
    }

//    public String getDiscountCode(){
//        return discountCodeProvider.getDiscountCode();
//    }
}
