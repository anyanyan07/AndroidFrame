package com.xwtec.androidframe.ui.myOrders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.ui.myOrders.bean.OrderMultiEntity;
import com.xwtec.androidframe.ui.myOrders.bean.OrderTab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

@Route(path = Constant.MY_ORDER_ROUTER)
public class MyOrderActivity extends BaseActivity<MyOrderPresenterImpl> implements MyOrderContact.MyOrderView {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private int curIndex = 0;
    private MyOrderAdapter adapter;
    private int curStatus = -1;
    private String[] tabNames = {"全部", "待付款", "待发货", "已发货", "已收货", "已取消", "已完成",
            "已退货", "已退款", "退货中", "退款中"};
    private int[] status = {-1, 2, 3, 4, 5, 10, 0, 8, 12, 7, 11};
    private List<OrderMultiEntity> data = new ArrayList<>();
    private OrderMultiEntity<OrderTab> tabEntity = new OrderMultiEntity<>();
    private OrderMultiEntity<Order> orderEntity = new OrderMultiEntity<>();

    @Override
    protected void init() {
        super.init();
        initTabData();
        initRv();
        curStatus = getIntent().getIntExtra(Constant.ORDER_STATUS, -1);
        //请求订单列表
        fetchOrderList(curStatus, curIndex);
    }

    private void initRv() {
        data.clear();
        data.add(tabEntity);
        data.add(orderEntity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyOrderAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }


    private void initTabData() {
        List<OrderTab> tabList = new ArrayList<>();
        for (int i = 0; i < tabNames.length; i++) {
            OrderTab orderTab = new OrderTab();
            orderTab.setName(tabNames[i]);
            orderTab.setStatus(status[i]);
            tabList.add(orderTab);
        }
        tabEntity.setDataList(tabList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    public void fetchOrderList(int status, int startIndex) {
        HashMap<String, Object> map = new HashMap<>();
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        map.put("token", userBean.getToken());
        map.put("startIndex", startIndex);
        map.put("showNumber", Constant.PER_PAGE_NUM);
        if (status != -1) {
            map.put("status", status);
        }
        presenter.fetchOrders(map);
    }

    @Override
    public void fetchOrdersSuccess(List<Order> orders) {
        if (curIndex == 0) {
            orderEntity.setDataList(orders);
        } else {
            orderEntity.getDataList().addAll(orders);
        }
        curIndex += orders.size();
        adapter.updateContent();
    }
}
