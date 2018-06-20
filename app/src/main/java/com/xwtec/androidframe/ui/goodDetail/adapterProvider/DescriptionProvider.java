package com.xwtec.androidframe.ui.goodDetail.adapterProvider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */
@ItemProviderTag(viewType = GoodDetailAdapter.DESCRIPTION_TYPE, layout = R.layout.good_detail_description)
public class DescriptionProvider extends BaseItemProvider<GoodDetailMultiEntity<GoodDetailResponse>, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse> data, int position) {
        GoodDetailResponse goodDetailResponse = data.getData();
        if (goodDetailResponse != null) {
            helper.setText(R.id.tv_name, goodDetailResponse.getTitle() + goodDetailResponse.getIntroduction());
            PriceView priceView = helper.getView(R.id.tv_cur_price);
            priceView.setPrice(goodDetailResponse.getDiscountPrice());
            helper.setText(R.id.tv_old_price, goodDetailResponse.getOriginalPrice());
        }
    }

    @Override
    public void onClick(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse> data, int position) {
        return false;
    }
}
