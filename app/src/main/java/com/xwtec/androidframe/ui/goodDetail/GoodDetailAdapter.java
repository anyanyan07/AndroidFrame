package com.xwtec.androidframe.ui.goodDetail;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.xwtec.androidframe.ui.goodDetail.adapterProvider.BannerProvider;
import com.xwtec.androidframe.ui.goodDetail.adapterProvider.DescriptionProvider;
import com.xwtec.androidframe.ui.goodDetail.adapterProvider.ImgsProvider;
import com.xwtec.androidframe.ui.goodDetail.adapterProvider.TxtProvider;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;

import java.util.List;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public class GoodDetailAdapter extends MultipleItemRvAdapter<GoodDetailMultiEntity, BaseViewHolder> {
    public static final int BANNER_TYPE = 0x01;
    public static final int DESCRIPTION_TYPE = 0x02;
    public static final int TXT_TYPE = 0x03;
    public static final int IMGS_TYPE = 0x04;

    public GoodDetailAdapter(@Nullable List<GoodDetailMultiEntity> data) {
        super(data);
        finishInitialize();
    }

    @Override
    protected int getViewType(GoodDetailMultiEntity goodDetailMultiEntity) {
        return goodDetailMultiEntity.getType();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new BannerProvider());
        mProviderDelegate.registerProvider(new DescriptionProvider());
        mProviderDelegate.registerProvider(new TxtProvider());
        mProviderDelegate.registerProvider(new ImgsProvider());
    }
}
