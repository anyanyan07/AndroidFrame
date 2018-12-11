package com.xwtec.androidframe.ui.home.adapterProvider;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.home.HomeAdapter;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;
import com.xwtec.androidframe.util.GridSpacingItemDecoration;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by ayy on 2018/6/17.
 * Describe:xxx
 */
@ItemProviderTag(viewType = HomeAdapter.HOME_CONTENT_TYPE, layout = R.layout.home_good_content)
public class ContentProvider extends BaseItemProvider<HomeMultiEntity<GoodListBean>, BaseViewHolder> {

    private HomeFragment homeFragment;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private RecyclerView recyclerView;

    public ContentProvider(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10), false);
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultiEntity<GoodListBean> data, int position) {
        final List<GoodListBean> goodBeanList = data.getData();
        recyclerView = helper.getView(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.removeItemDecoration(gridSpacingItemDecoration);
        recyclerView.addItemDecoration(gridSpacingItemDecoration);
        BaseQuickAdapter adapter = new BaseQuickAdapter<GoodListBean, BaseViewHolder>(R.layout.home_content_layout, goodBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, final GoodListBean goodListBean) {
                ImageLoadUtil.loadFitCenter(mContext, goodListBean.getImgUrl(), (ImageView) helper.getView(R.id.iv_good));
                helper.setText(R.id.tv_good_name, goodListBean.getTitle());
                helper.setText(R.id.tv_good_num, goodListBean.getIntroduction());
                PriceView priceView = helper.getView(R.id.tv_cur_price);
                priceView.setPrice(goodListBean.getDiscountPrice());
                helper.setText(R.id.tv_old_price, goodListBean.getOriginalPrice());
                final ImageView ivShopCart = helper.getView(R.id.iv_shop_cart);
                ivShopCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homeFragment.addShopCart(goodListBean.getId(), ivShopCart);
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(Constant.GOODS_DETAIL_ROUTER)
                        .withLong("goodId", goodBeanList.get(position).getId())
                        .navigation();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(BaseViewHolder helper, HomeMultiEntity<GoodListBean> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, HomeMultiEntity<GoodListBean> data, int position) {
        return false;
    }

}
