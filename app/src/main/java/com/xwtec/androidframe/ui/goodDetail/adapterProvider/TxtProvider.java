package com.xwtec.androidframe.ui.goodDetail.adapterProvider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */
@ItemProviderTag(viewType = GoodDetailAdapter.TXT_TYPE, layout = R.layout.good_detail_txt)
public class TxtProvider extends BaseItemProvider<GoodDetailMultiEntity, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, GoodDetailMultiEntity data, int position) {

    }

    @Override
    public void onClick(BaseViewHolder helper, GoodDetailMultiEntity data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, GoodDetailMultiEntity data, int position) {
        return false;
    }
}
