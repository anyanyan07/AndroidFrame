package com.xwtec.androidframe.ui.goodDetail.adapterProvider;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
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
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */
@ItemProviderTag(viewType = GoodDetailAdapter.BANNER_TYPE, layout = R.layout.good_detail_banner)
public class BannerProvider extends BaseItemProvider<GoodDetailMultiEntity<GoodDetailResponse.DetailImgListBean>, BaseViewHolder> {

    @Override
    public void convert(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse.DetailImgListBean> goodDetailMultiEntity, int position) {
        List<GoodDetailResponse.DetailImgListBean> data = goodDetailMultiEntity.getDataList();
        if (data == null) {
            return;
        }
        final List<String> imgUrls = new ArrayList<>();
        Flowable.fromIterable(data)
                .forEach(new Consumer<GoodDetailResponse.DetailImgListBean>() {
                    @Override
                    public void accept(GoodDetailResponse.DetailImgListBean bannerBean) throws Exception {
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
        banner.isAutoPlay(false);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imgUrls);
        banner.start();
    }

    @Override
    public void onClick(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse.DetailImgListBean> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse.DetailImgListBean> data, int position) {
        return false;
    }
}
