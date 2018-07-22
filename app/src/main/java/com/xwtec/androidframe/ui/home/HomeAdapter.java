package com.xwtec.androidframe.ui.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.xwtec.androidframe.ui.home.adapterProvider.BannerProvider;
import com.xwtec.androidframe.ui.home.adapterProvider.ContentProvider;
import com.xwtec.androidframe.ui.home.adapterProvider.TabTitleProvider;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;

import java.util.List;

/**
 * Created by ayy on 2018/6/17.
 * Describe:xxx
 */

public class HomeAdapter extends MultipleItemRvAdapter<HomeMultiEntity, BaseViewHolder> {
    public static final int HOME_BANNER_TYPE = 0x001;
    public static final int HOME_TITLE_TYPE = 0x002;
    public static final int HOME_CONTENT_TYPE = 0x003;

    private TabTitleProvider tabTitleProvider;
    private ContentProvider contentProvider;

    HomeAdapter(@Nullable List<HomeMultiEntity> data, HomeFragment homeFragment) {
        super(data);
        tabTitleProvider = new TabTitleProvider(homeFragment, this);
        contentProvider = new ContentProvider(homeFragment);
        finishInitialize();
    }

    @Override
    protected int getViewType(HomeMultiEntity homeMultiEntity) {
        return homeMultiEntity.getItemType();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new BannerProvider());
        mProviderDelegate.registerProvider(tabTitleProvider);
        mProviderDelegate.registerProvider(contentProvider);
    }

    void updateBanner() {
        notifyItemChanged(0);
    }

    public void updateTab(int selectedPosition) {
        tabTitleProvider.update(selectedPosition);
    }

    void updateGoodContent() {
        notifyItemChanged(2);
    }

}
