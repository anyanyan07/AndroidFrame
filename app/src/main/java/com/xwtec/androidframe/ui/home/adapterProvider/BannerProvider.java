package com.xwtec.androidframe.ui.home.adapterProvider;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.home.HomeAdapter;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by ayy on 2018/6/17.
 * Describe:xxx
 */
@ItemProviderTag(viewType = HomeAdapter.HOME_BANNER_TYPE, layout = R.layout.home_banner)
public class BannerProvider extends BaseItemProvider<HomeMultiEntity<BannerBean>, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, HomeMultiEntity<BannerBean> homeMultiEntity, int position) {
        List<BannerBean> data = homeMultiEntity.getData();
        if (data == null) {
            return;
        }
        final List<String> imgUrls = new ArrayList<>();
        Flowable.fromIterable(data)
                .forEach(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        imgUrls.add(bannerBean.getImgUrl());
                    }
                });
        final Banner banner = helper.getView(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageLoadUtil.load(context, path, imageView);
            }
        });
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imgUrls);
        banner.start();
    }

    @Override
    public void onClick(BaseViewHolder helper, HomeMultiEntity<BannerBean> homeMultiEntity, int position) {
        ToastUtils.showShort(homeMultiEntity.getData().get(position).getId());
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, HomeMultiEntity<BannerBean> homeMultiEntity, int position) {
        return false;
    }
}
