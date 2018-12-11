package com.xwtec.androidframe.ui.myOrders;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.myOrders.bean.Order;
import com.xwtec.androidframe.ui.myOrders.bean.OrderTab;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;
import com.xwtec.androidframe.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

@Route(path = Constant.MY_ORDER_ROUTER)
public class MyOrderActivity extends BaseActivity<MyOrderPresenterImpl> implements MyOrderContact.MyOrderView, OnRefreshLoadMoreListener {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tab_rv)
    RecyclerView rvTab;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    private int curIndex = 0;
    private int curStatus = -1;
    private String[] tabNames = {"全部", "待付款", "待发货", "已发货", "已收货", "已取消", "已完成",
            "已退货", "已退款", "退货中", "退款中"};
    private int[] status = {-1, 2, 3, 4, 5, 10, 0, 8, 12, 7, 11};
    private List<OrderTab> tabData;
    private List<Order> orderList = new ArrayList<>();
    private BaseQuickAdapter<Order, BaseViewHolder> contentAdapter;
    private String token;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.myOrders);
        curStatus = getIntent().getIntExtra(Constant.ORDER_STATUS, -1);
        initTabData();
        initRvTab();
        initRv();
        initRxBus();
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        token = userBean.getToken();
        refreshLayout.setOnRefreshLoadMoreListener(this);
        //请求订单列表
        fetchOrderList(curStatus, curIndex);
    }

    private void initRxBus() {
        RxBus.getInstance().register(RxBusMSG.class, new Consumer<RxBusMSG>() {
            @Override
            public void accept(RxBusMSG rxBusMSG) throws Exception {
                switch (rxBusMSG.getCode()) {
                    case Constant.RX_ORDER_CHANGE:
                        int[] data = (int[]) rxBusMSG.getData();
                        int position = data[0];
                        int status = data[1];
                        if (curStatus == -1 && status != Constant.DELETED) {
                            orderList.get(position).setStatus(status);
                            contentAdapter.notifyItemChanged(position);
                        } else {
                            orderList.remove(position);
                            contentAdapter.remove(position);
                        }
                        break;
                    case Constant.RX_COMMENT_CHANGE:
                        int changePos = (int) rxBusMSG.getData();
                        orderList.get(changePos).setIsComment(1);
                        contentAdapter.notifyItemChanged(changePos);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initRvTab() {
        rvTab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        BaseQuickAdapter<OrderTab, BaseViewHolder> adapter = new BaseQuickAdapter<OrderTab, BaseViewHolder>(R.layout.home_title_layout, tabData) {
            @Override
            protected void convert(BaseViewHolder helper, OrderTab item) {
                helper.setText(R.id.tv_title, item.getName());
                helper.getView(R.id.item_title).setSelected(curStatus == item.getStatus());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                curStatus = tabData.get(position).getStatus();
                adapter.notifyDataSetChanged();
                fetchOrderList(curStatus, 0);
            }
        });
        rvTab.setAdapter(adapter);
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new SpacesItemDecoration("bottom", ConvertUtils.dp2px(10)));
        contentAdapter = new BaseQuickAdapter<Order, BaseViewHolder>(R.layout.order_item, orderList) {
            @Override
            protected void convert(final BaseViewHolder helper, final Order item) {
                ImageLoadUtil.loadFitCenter(mContext, item.getImgUrl(), (ImageView) helper.getView(R.id.iv_good));
                helper.setText(R.id.tv_order_num, "订单号：" + item.getOrderNumber());
                helper.setText(R.id.tv_good_name, item.getTitle() + item.getIntroduction());
                helper.setText(R.id.tv_good_unit_num, "x" + item.getGoodsNumber());
                PriceView goodPrice = helper.getView(R.id.good_price);
                goodPrice.setPrice(item.getUnitPrice());
                helper.setText(R.id.tv_total_good_num, "共" + item.getGoodsNumber() + "件商品");
                PriceView priceView = helper.getView(R.id.total_price);
                priceView.setPrice(item.getTotalPrice());
                helper.setText(R.id.tv_express, "含运费￥" + item.getExpressPrice());
                LinearLayout llManager = helper.getView(R.id.ll_manager);
                TextView tvSure = helper.getView(R.id.tv_sure);
                TextView tvCancel = helper.getView(R.id.tv_cancel);
                TextView tvDelete = helper.getView(R.id.tv_delete);
                TextView tvComment = helper.getView(R.id.tv_comment);
                TextView tvPay = helper.getView(R.id.tv_pay);
                TextView tvSureReceive = helper.getView(R.id.tv_sure_receive);
                TextView tvSalesReturn = helper.getView(R.id.tv_sales_return);
                TextView tvMoneyReturn = helper.getView(R.id.tv_money_return);
                TextView tvExpressInfo = helper.getView(R.id.tv_express_info);
                allGone(llManager, tvCancel, tvDelete, tvPay, tvSalesReturn, tvMoneyReturn, tvSalesReturn, tvSure, tvSureReceive, tvComment, tvExpressInfo);
                final int status = item.getStatus();
                switch (status) {
                    case 0://订单完成
                        helper.setText(R.id.tv_status, "已完成");
                        tvVisible(llManager, tvDelete, tvExpressInfo);
                        break;
                    case 1://已删除
                        break;
                    case 2://待付款
                        helper.setText(R.id.tv_status, "待付款");
                        tvVisible(llManager, tvCancel, tvPay);
                        break;
                    case 3://已付款与待发货
                        helper.setText(R.id.tv_status, "待发货");
                        tvVisible(llManager, tvMoneyReturn);
                        break;
                    case 4://已发货与路途中
                        helper.setText(R.id.tv_status, "已发货");
                        tvVisible(llManager, tvExpressInfo);
                        break;
                    case 5://已收货
                        helper.setText(R.id.tv_status, "已收货");
                        tvVisible(llManager, tvSureReceive, tvSalesReturn,tvExpressInfo);
                        break;
                    case 6://用户确认收货
                        helper.setText(R.id.tv_status, "已确认收货");
                        tvVisible(llManager, tvDelete,tvExpressInfo);
                        if (item.getIsComment() == 0) {
                            tvComment.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 7://退货中
                        helper.setText(R.id.tv_status, "退货中");
                        tvVisible(llManager, tvExpressInfo);
                        break;
                    case 8://已退货与退货完成
                        helper.setText(R.id.tv_status, "已退货");
                        tvVisible(llManager, tvDelete,tvExpressInfo);
                        break;
                    case 9://取消订单中
                        helper.setText(R.id.tv_status, "取消中");
                        break;
                    case 10://已取消与取消完成
                        helper.setText(R.id.tv_status, "已取消");
                        tvVisible(llManager, tvDelete);
                        break;
                    case 11://退款中
                        helper.setText(R.id.tv_status, "退款中");
                        break;
                    case 12://已退款
                        helper.setText(R.id.tv_status, "已退款");
                        tvVisible(llManager, tvDelete);
                        break;
                    default:
                        break;
                }
                helper.getView(R.id.ll_go_detail).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到订单详情页
                        gotoDetail(item.getOrderId(),item.getGoodsId(), status, helper.getAdapterPosition());
                    }
                });
                //删除订单
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.deleteOrder(item.getOrderId() + "", token, helper.getAdapterPosition());
                    }
                });
                //查看物流
                tvExpressInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.EXPRESS_INFO_ROUTER)
                                .withString("orderNum", item.getOrderNumber()).navigation();
                    }
                });

                //立即付款
                tvPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.PAY_ROUTER)
                                .withString("totalMoney", item.getTotalPrice());
                    }
                });
                //取消订单
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.cancelOrder(item.getOrderId(), token, helper.getAdapterPosition());
                    }
                });
                //确认收货
                tvSureReceive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.sureReceive(item.getOrderId() + "", token, helper.getAdapterPosition());
                    }
                });
                //退货
                tvSalesReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.SALE_RETURN_ROUTER)
                                .withSerializable("order", item)
                                .withInt("position", helper.getAdapterPosition()).navigation();
                    }
                });
                //退款
                tvMoneyReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.MONEY_RETURN_ROUTER)
                                .withLong("orderId", item.getOrderId())
                                .withInt("position", helper.getAdapterPosition()).navigation();
                    }
                });
                //评价
                tvComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(Constant.COMMENT_ROUTER)
                                .withString("orderNumber", item.getOrderNumber())
                                .withInt("position", helper.getAdapterPosition()).navigation();
                    }
                });
            }
        };
        contentAdapter.setEmptyView(R.layout.empty_layout, llContent);
        recyclerView.setAdapter(contentAdapter);
    }

    private void gotoDetail(long orderId,long goodId, int status, int position) {
        switch (status) {
            case 1://已删除
                break;
            case 0://已完成
            case 2://待付款
            case 10://已取消与取消完成
            case 4://已发货与路途中:待收货
            case 5://已收货
            case 3://已付款与待发货
            case 9://取消订单中
            case 6://用户已确认收货
                ARouter.getInstance().build(Constant.ORDER_DETAIL_ROUTER)
                        .withLong("orderId", orderId)
                        .withLong("goodId", goodId)
                        .withInt("status", status)
                        .withInt("position", position)
                        .navigation();
                break;
            case 7://退货中
            case 8://已退货与退货完成
            case 11://退款中
            case 12://已退款
                ARouter.getInstance().build(Constant.REFUND_DETAIL_ROUTER)
                        .withLong("orderId", orderId)
                        .withLong("goodId", goodId)
                        .withInt("status", status)
                        .withInt("position", position)
                        .navigation();
                break;
            default:
                break;
        }

    }

    private void allGone(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    private void tvVisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void initTabData() {
        tabData = new ArrayList<>();
        for (int i = 0; i < tabNames.length; i++) {
            OrderTab orderTab = new OrderTab();
            orderTab.setName(tabNames[i]);
            orderTab.setStatus(status[i]);
            tabData.add(orderTab);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    public void fetchOrderList(int status, int startIndex) {
        curStatus = status;
        curIndex = startIndex;
        HashMap<String, Object> map = new HashMap<>();

        map.put("token", token);
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
            refreshLayout.finishRefresh(true);
            orderList = orders;
            contentAdapter.setNewData(orderList);
        } else {
            refreshLayout.finishLoadMore(true);
            orderList.addAll(orders);
            contentAdapter.addData(orders);
        }
        if (orders != null) {
            refreshLayout.setNoMoreData(orders.size() < Constant.PER_PAGE_NUM);
            curIndex += orders.size();
        }
    }

    @Override
    public void showLoadFail(String msg) {
        super.showLoadFail(msg);
        if (curIndex == 0) {
            refreshLayout.finishRefresh(false);
        } else {
            refreshLayout.finishLoadMore(false);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        fetchOrderList(curStatus, curIndex);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        fetchOrderList(curStatus, 0);
    }

    @Override
    public void deleteSuccess(int position) {
        orderList.remove(position);
        contentAdapter.remove(position);
    }

    @Override
    public void cancelSuccess(int position) {
        if (curStatus == Constant.WAIT_PAY) {
            orderList.remove(position);
            contentAdapter.remove(position);
        } else {
            //改变订单状态
            orderList.get(position).setStatus(Constant.CANCELING);
            contentAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void sureReceiveSuccess(int position) {
        if (curStatus == Constant.RECEIVED) {
            orderList.remove(position);
            contentAdapter.remove(position);
        } else {
            //改变订单状态
            orderList.get(position).setStatus(Constant.SURE_RECEIVED);
            contentAdapter.notifyItemChanged(position);
        }
    }

    @OnClick({R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            default:
                break;
        }

    }


}
