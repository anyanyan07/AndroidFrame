package com.xwtec.androidframe.ui.myOrders.privider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.myOrders.MyOrderActivity;
import com.xwtec.androidframe.ui.myOrders.MyOrderAdapter;
import com.xwtec.androidframe.ui.myOrders.bean.OrderMultiEntity;
import com.xwtec.androidframe.ui.myOrders.bean.OrderTab;

import java.util.List;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */
@ItemProviderTag(viewType = MyOrderAdapter.TAB_TYPE, layout = R.layout.order_tab)
public class TabProvider extends BaseItemProvider<OrderMultiEntity<OrderTab>, BaseViewHolder> {
    private int selectedPosition;
    private MyOrderAdapter myOrderAdapter;
    private MyOrderActivity myOrderActivity;

    public TabProvider(MyOrderAdapter myOrderAdapter, MyOrderActivity myOrderActivity) {
        this.myOrderAdapter = myOrderAdapter;
        this.myOrderActivity = myOrderActivity;
    }

    @Override
    public void convert(BaseViewHolder helper, OrderMultiEntity<OrderTab> data, int position) {
        List<OrderTab> orderTabList = data.getDataList();
        RecyclerView recyclerView = helper.getView(R.id.rv_order_tab);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        BaseQuickAdapter<OrderTab, BaseViewHolder> adapter = new BaseQuickAdapter<OrderTab, BaseViewHolder>(R.layout.home_title_layout, orderTabList) {
            @Override
            protected void convert(BaseViewHolder helper, OrderTab item) {
                helper.setText(R.id.tv_title, item.getName());
                helper.getView(R.id.item_title).setSelected(selectedPosition == helper.getAdapterPosition());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectedPosition = position;
                myOrderAdapter.updateTab();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(BaseViewHolder helper, OrderMultiEntity<OrderTab> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, OrderMultiEntity<OrderTab> data, int position) {
        return false;
    }
}
