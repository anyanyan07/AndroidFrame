package com.xwtec.androidframe.ui.myOrders;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.xwtec.androidframe.ui.myOrders.bean.OrderMultiEntity;
import com.xwtec.androidframe.ui.myOrders.privider.ContentProvider;
import com.xwtec.androidframe.ui.myOrders.privider.TabProvider;

import java.util.List;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */

public class MyOrderAdapter extends MultipleItemRvAdapter<OrderMultiEntity, BaseViewHolder> {
    public static final int TAB_TYPE = 0x01;
    public static final int CONTENT_TYPE = 0x02;
    private TabProvider tabProvider;
    private ContentProvider contentProvider;

    public MyOrderAdapter(@Nullable List<OrderMultiEntity> data, MyOrderActivity myOrderActivity) {
        super(data);
        tabProvider = new TabProvider(this, myOrderActivity);
        contentProvider = new ContentProvider(this, myOrderActivity);
    }

    @Override
    protected int getViewType(OrderMultiEntity orderMultiEntity) {
        return orderMultiEntity.getType();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(tabProvider);
        mProviderDelegate.registerProvider(contentProvider);
    }

    public void updateTab() {
        notifyItemChanged(0);
    }

    public void updateContent() {
        notifyItemChanged(1);
    }
}
