package com.xwtec.androidframe.ui.shopCart;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.ui.shopCart.bean.ShopCartBean;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends BaseFragment<ShopCartPresenterImpl> implements ShopCartContact.ShopCartView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private BaseQuickAdapter<ShopCartBean, BaseViewHolder> adapter;
    private List<ShopCartBean> shopCartBeanList = new ArrayList<>();
    private int pageIndex = 0;

    @Inject
    public ShopCartFragment() {
    }

    @Override
    protected void init() {
        tvTitle.setText("购物车");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("编辑");
        initRecyclerView();
        fetchShopCartData(pageIndex);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new BaseQuickAdapter<ShopCartBean, BaseViewHolder>(R.layout.shopcart_item, shopCartBeanList) {
            @Override
            protected void convert(final BaseViewHolder helper, final ShopCartBean shopCartBean) {
                ImageLoadUtil.loadCenterCrop(context, shopCartBean.getImgUrl(), (ImageView) helper.getView(R.id.iv_item));
                helper.setText(R.id.tv_name, shopCartBean.getTitle() + shopCartBean.getIntroduction());
                helper.setText(R.id.tv_price, shopCartBean.getDiscountPrice());
                final int goodsNumber = shopCartBean.getGoodsNumber();
                TextView tvNum = helper.getView(R.id.tv_num);
                tvNum.setText(goodsNumber + "");
                TextView tvReduce = helper.getView(R.id.tv_reduce);
                tvReduce.setClickable(goodsNumber > 1);
                tvReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateShopCart(shopCartBean.getId(), goodsNumber - 1, shopCartBean, helper.getAdapterPosition());
                    }
                });
                helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateShopCart(shopCartBean.getId(), goodsNumber + 1, shopCartBean, helper.getAdapterPosition());
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到商品详情页
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_cart;
    }

    private void fetchShopCartData(int startIndex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startIndex", startIndex);
        map.put("showNumber", 20);
        presenter.fetchShopCartData(map);
    }

    @Override
    public void fetchShopCartSuccess(List<ShopCartBean> shopCartBeanList) {
        this.shopCartBeanList.addAll(shopCartBeanList);
        pageIndex++;
        adapter.notifyDataSetChanged();
    }

    private void updateShopCart(long id, int goodsNum, ShopCartBean shopCartBean, int position) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("goodsNumber", goodsNum);
        presenter.updateShopCart(map, shopCartBean, position);
    }

    @Override
    public void updateShopCartSuccess(ShopCartBean shopCartBean, int goodsNum, int position) {
        shopCartBean.setGoodsNumber(goodsNum);
        adapter.notifyItemChanged(position);
    }


    @Override
    public void deleteFromShopCartSuccess() {

    }


}
