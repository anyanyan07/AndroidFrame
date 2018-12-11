package com.xwtec.androidframe.ui.shopCart;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.base.MessageEvent;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;
import com.xwtec.androidframe.util.ImageLoadUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment<ShopCartPresenterImpl> implements ShopCartContact.ShopCartView, OnRefreshListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.ll_select_all_del)
    LinearLayout llSelectAllDel;
    @BindView(R.id.ll_select_all)
    LinearLayout llSelectAll;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.total_price)
    PriceView totalPrice;

    private boolean isEditing;
    private BaseQuickAdapter<ShopCartBean, BaseViewHolder> adapter;
    private List<ShopCartBean> shopCartBeanList = new ArrayList<>();
    private List<ShopCartBean> selectedList = new ArrayList<>();
    private int startIndex = 0;
    private int selectedSize;
    private UserBean userBean;
    private BigDecimal totalMoney = new BigDecimal(0);

    @Inject
    public ShopCartFragment() {
    }

    @Override
    protected void init() {
        ivLeft.setVisibility(View.GONE);
        tvTitle.setText("购物车");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("编辑");
        totalPrice.setPrice("0");
        initRecyclerView();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        EventBus.getDefault().register(this);
        fetchShopCartData(startIndex);
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new BaseQuickAdapter<ShopCartBean, BaseViewHolder>(R.layout.shopcart_item, shopCartBeanList) {
            @Override
            protected void convert(final BaseViewHolder helper, final ShopCartBean shopCartBean) {
                helper.getView(R.id.iv_check).setSelected(shopCartBean.isSelected());
                ImageLoadUtil.loadCenterCrop(context, shopCartBean.getImgUrl(), (ImageView) helper.getView(R.id.iv_item));
                helper.setText(R.id.tv_name, shopCartBean.getTitle() + shopCartBean.getIntroduction());
                PriceView priceView = helper.getView(R.id.tv_price);
                priceView.setPrice(shopCartBean.getDiscountPrice());
                final int goodsNumber = shopCartBean.getGoodsNumber();
                TextView tvNum = helper.getView(R.id.tv_num);
                tvNum.setText(goodsNumber + "");
                TextView tvReduce = helper.getView(R.id.tv_reduce);
                tvReduce.setClickable(goodsNumber > 1);
                tvReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateShopCart(shopCartBean.getId(), goodsNumber - 1, shopCartBean, helper.getAdapterPosition(), false);
                    }
                });
                helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateShopCart(shopCartBean.getId(), goodsNumber + 1, shopCartBean, helper.getAdapterPosition(), true);
                    }
                });
                final ImageView ivCheck = helper.getView(R.id.iv_check);
                ivCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean selected = ivCheck.isSelected();
                        shopCartBean.setSelected(!selected);
                        BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble(shopCartBean.getDiscountPrice()));
                        BigDecimal num = BigDecimal.valueOf(shopCartBean.getGoodsNumber());
                        BigDecimal money = unitPrice.multiply(num);
                        adapter.notifyItemChanged(helper.getAdapterPosition());
                        if (selected) {
                            selectedSize--;
                            totalMoney = totalMoney.subtract(money);
                        } else {
                            selectedSize++;
                            totalMoney = totalMoney.add(money);
                        }
                        totalPrice.setPrice(totalMoney.toString());
                        llSelectAllDel.setSelected(selectedSize == shopCartBeanList.size());
                        llSelectAll.setSelected(selectedSize == shopCartBeanList.size());
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到商品详情页
                ARouter.getInstance().build(Constant.GOODS_DETAIL_ROUTER)
                        .withLong("goodId", shopCartBeanList.get(position).getGoodsId())
                        .navigation();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_cart;
    }

    public void fetchShopCartData(int startIndex) {
        this.startIndex = startIndex;
        totalMoney = new BigDecimal(0);
        totalPrice.setPrice("0");
        showLoading();
        HashMap<String, Object> map = new HashMap<>();
        map.put("startIndex", startIndex);
        map.put("showNumber", 200);
        map.put("token", userBean.getToken());
        presenter.fetchShopCartData(map);
    }

    @Override
    public void fetchShopCartSuccess(List<ShopCartBean> shopCartBeanList) {
        dismissLoading();
        this.shopCartBeanList.clear();
        selectedList.clear();
        refreshLayout.finishRefresh(true);
        this.shopCartBeanList.addAll(shopCartBeanList);
        adapter.notifyDataSetChanged();
        llSelectAllDel.setSelected(false);
        llSelectAll.setSelected(false);
        selectedList.clear();
        selectedSize = 0;
        dataNotify();
    }

    @Override
    public void showLoadFail(String msg) {
        super.showLoadFail(msg);
        refreshLayout.finishRefresh(false);
    }

    private void updateShopCart(long id, int goodsNum, ShopCartBean shopCartBean, int position, boolean isAdd) {
        showLoading();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("goodsNumber", goodsNum);
        map.put("token", userBean.getToken());
        presenter.updateShopCart(map, shopCartBean, position, isAdd);
    }

    @Override
    public void updateShopCartSuccess(ShopCartBean shopCartBean, int goodsNum, int position, boolean isAdd) {
        dismissLoading();
        shopCartBean.setGoodsNumber(goodsNum);
        adapter.notifyItemChanged(position);
        boolean selected = shopCartBean.isSelected();
        if (selected) {
            if (isAdd) {
                totalMoney = totalMoney.add(BigDecimal.valueOf(Double.parseDouble(shopCartBean.getDiscountPrice())));
            } else {
                totalMoney = totalMoney.subtract(BigDecimal.valueOf(Double.parseDouble(shopCartBean.getDiscountPrice())));
            }
            totalPrice.setPrice(totalMoney.toString());
        }
    }


    @Override
    public void deleteFromShopCartSuccess() {
        dismissLoading();
        shopCartBeanList.removeAll(selectedList);
        adapter.notifyDataSetChanged();
        dataNotify();
    }

    @OnClick({R.id.tv_right, R.id.ll_select_all, R.id.btn_pay, R.id.ll_select_all_del, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                if (isEditing) {
                    tvRight.setText(R.string.edit);
                    if (shopCartBeanList != null && !shopCartBeanList.isEmpty()) {
                        llPay.setVisibility(View.VISIBLE);
                    }
                    llDelete.setVisibility(View.GONE);
                    isEditing = false;
                } else {
                    tvRight.setText(R.string.finish);
                    isEditing = true;
                    if (shopCartBeanList != null && !shopCartBeanList.isEmpty()) {
                        llDelete.setVisibility(View.VISIBLE);
                    }
                    llPay.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_select_all:
                selectAll(llSelectAll);
                break;
            case R.id.ll_select_all_del:
                selectAll(llSelectAllDel);
                break;
            case R.id.btn_pay:
                try {
                    JSONArray jsonArray = new JSONArray();
                    for (ShopCartBean shopCartBean : shopCartBeanList) {
                        if (shopCartBean.isSelected()) {
                            JSONObject params = new JSONObject();
                            params.put("shopCarId", shopCartBean.getId());
                            params.put("goodsId", shopCartBean.getGoodsId());
                            params.put("goodsNumber", shopCartBean.getGoodsNumber());
                            jsonArray.put(params);
                        }
                    }
                    if (jsonArray.length() <= 0) {
                        ToastUtils.showShort("请选择商品");
                        return;
                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("token", userBean.getToken());
                    jsonObject.put("parameter", jsonArray);
                    ARouter.getInstance().build(Constant.AFFIRM_ORDER_ROUTER)
                            .withString("json", jsonObject.toString()).navigation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_delete:
                showLoading();
                selectedList.clear();
                StringBuffer ids = new StringBuffer();
                for (ShopCartBean shopCartBean : shopCartBeanList) {
                    if (shopCartBean.isSelected()) {
                        ids.append(shopCartBean.getId() + ",");
                        selectedList.add(shopCartBean);
                    }
                }
                String id = ids.substring(0, ids.length());
                presenter.deleteShopCart(id, userBean.getToken());
                break;
            default:
                break;
        }
    }

    private void selectAll(View view) {
        showLoading();
        selectedSize = shopCartBeanList.size();
        boolean selected = view.isSelected();
        totalMoney = BigDecimal.ZERO;
        llSelectAll.setSelected(!selected);
        llSelectAllDel.setSelected(!selected);
        for (ShopCartBean shopCartBean : shopCartBeanList) {
            shopCartBean.setSelected(!selected);
            if (!selected) {
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(shopCartBean.getDiscountPrice()));
                BigDecimal num = BigDecimal.valueOf(shopCartBean.getGoodsNumber());
                totalMoney = totalMoney.add(price.multiply(num));
            }
        }
        totalPrice.setPrice(totalMoney.toString());
        adapter.notifyDataSetChanged();
        dismissLoading();
    }

    private void dataNotify() {
        totalMoney = new BigDecimal(0);
        totalPrice.setPrice("0");
        if (shopCartBeanList != null && !shopCartBeanList.isEmpty()) {
            recyclerView.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
            if (isEditing) {
                llPay.setVisibility(View.GONE);
                llDelete.setVisibility(View.VISIBLE);
            } else {
                llPay.setVisibility(View.VISIBLE);
                llDelete.setVisibility(View.GONE);
            }
        } else {
            recyclerView.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
            llPay.setVisibility(View.GONE);
            llDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        startIndex = 0;
        fetchShopCartData(startIndex);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            fetchShopCartData(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        fetchShopCartData(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
