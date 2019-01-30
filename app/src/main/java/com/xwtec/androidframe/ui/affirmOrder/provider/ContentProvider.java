package com.xwtec.androidframe.ui.affirmOrder.provider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.ui.MultiEntity;
import com.xwtec.androidframe.ui.affirmOrder.AffirmAdapter;
import com.xwtec.androidframe.ui.affirmOrder.bean.AffirmResponse;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by ayy on 2018/6/27.
 * Describe:xxx
 */
@ItemProviderTag(viewType = AffirmAdapter.GOODS_TYPE, layout = R.layout.simple_recycler)
public class ContentProvider extends BaseItemProvider<MultiEntity<AffirmResponse.OrderGoodsBean>, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, MultiEntity<AffirmResponse.OrderGoodsBean> data, int position) {
        List<AffirmResponse.OrderGoodsBean> dataList = data.getDataList();
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        RecyclerView recyclerView = helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BaseQuickAdapter<AffirmResponse.OrderGoodsBean, BaseViewHolder> adapter = new BaseQuickAdapter<AffirmResponse.OrderGoodsBean, BaseViewHolder>(R.layout.affirm_order_item, dataList) {
            @Override
            protected void convert(BaseViewHolder helper, AffirmResponse.OrderGoodsBean item) {
                ImageView ivGood = helper.getView(R.id.iv_good);
                ImageLoadUtil.loadFitCenter(mContext, item.getImgUrl(), ivGood);
                helper.setText(R.id.tv_good_name, item.getTitle() + item.getIntroduction());
                PriceView unitPriceView = helper.getView(R.id.good_price);
                unitPriceView.setPrice(item.getUnitPrice());
                helper.setText(R.id.tv_good_unit_num, "x" + item.getGoodsNumber());
                helper.setText(R.id.tv_good_num, "共" + item.getGoodsNumber() + "件商品");
                PriceView priceView = helper.getView(R.id.subtotal_money);
                priceView.setPrice(item.getTotalPrice());
                PriceView expressPriceView = helper.getView(R.id.express_price);
                expressPriceView.setPrice(item.getFreight());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(BaseViewHolder helper, MultiEntity<AffirmResponse.OrderGoodsBean> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultiEntity<AffirmResponse.OrderGoodsBean> data, int position) {
        return false;
    }
}
