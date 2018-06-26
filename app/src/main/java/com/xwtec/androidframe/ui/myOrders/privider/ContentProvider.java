package com.xwtec.androidframe.ui.myOrders.privider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.myOrders.MyOrderActivity;
import com.xwtec.androidframe.ui.myOrders.MyOrderAdapter;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.ui.myOrders.bean.OrderMultiEntity;

import java.util.List;

/**
 * Created by ayy on 2018/6/25.
 * Describe:xxx
 */
@ItemProviderTag(viewType = MyOrderAdapter.CONTENT_TYPE, layout = R.layout.home_good_content)
public class ContentProvider extends BaseItemProvider<OrderMultiEntity<Order>, BaseViewHolder> {
    private MyOrderAdapter myOrderAdapter;
    private MyOrderActivity myOrderActivity;

    public ContentProvider(MyOrderAdapter myOrderAdapter, MyOrderActivity myOrderActivity) {
        this.myOrderAdapter = myOrderAdapter;
        this.myOrderActivity = myOrderActivity;
    }

    @Override
    public void convert(BaseViewHolder helper, OrderMultiEntity<Order> data, int position) {
        final List<Order> orderList = data.getDataList();
        RecyclerView recyclerView = helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        BaseQuickAdapter<Order, BaseViewHolder> adapter = new BaseQuickAdapter<Order, BaseViewHolder>(R.layout.order_item, orderList) {
            @Override
            protected void convert(BaseViewHolder helper, Order item) {
//                ImageLoadUtil.loadFitCenter(mContext,);
                helper.setText(R.id.tv_order_num, "订单号：" + item.getOrderNumber());
                helper.setText(R.id.tv_good_name, item.getTitle() + item.getIntroduction());
                helper.setText(R.id.tv_good_unit_num, "x" + item.getGoodsNumber());
                helper.setText(R.id.good_price, item.getUnitPrice());
                helper.setText(R.id.tv_total_good_num, "共" + item.getGoodsNumber() + "件商品");
                PriceView priceView = helper.getView(R.id.total_price);
                priceView.setPrice(item.getTotalPrice());
                helper.setText(R.id.tv_express, "含运费￥" + item.getExpressPrice());
                int status = item.getStatus();
                switch (status) {
                    case 0://订单完成
                        helper.setText(R.id.tv_status, "已完成");
                        break;
                    case 1://已删除
                        break;
                    case 2://待付款
                        helper.setText(R.id.tv_status, "待付款");
                        break;
                    case 3://已付款与待发货
                        helper.setText(R.id.tv_status, "待发货");
                        break;
                    case 4://已发货与路途中
                        helper.setText(R.id.tv_status, "已发货");
                        break;
                    case 5://已收货
                        helper.setText(R.id.tv_status, "已收货");
                        break;
                    case 6://用户确认收货
                        break;
                    case 7://退货中
                        helper.setText(R.id.tv_status, "退货中");
                        break;
                    case 8://已退货与退货完成
                        helper.setText(R.id.tv_status, "已退货");
                        break;
                    case 9://取消订单中
                        helper.setText(R.id.tv_status, "取消中");
                        break;
                    case 10://已取消与取消完成
                        helper.setText(R.id.tv_status, "已取消");
                        break;
                    case 11://退款中
                        helper.setText(R.id.tv_status, "退款中");
                        break;
                    case 12://已退款
                        helper.setText(R.id.tv_status, "已退款");
                        break;
                    default:
                        break;

                }
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(Constant.GOODS_DETAIL_ROUTER)
                        .withLong("goodId", orderList.get(position).getGoodsId())
                        .navigation();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(BaseViewHolder helper, OrderMultiEntity<Order> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, OrderMultiEntity<Order> data, int position) {
        return false;
    }
}
