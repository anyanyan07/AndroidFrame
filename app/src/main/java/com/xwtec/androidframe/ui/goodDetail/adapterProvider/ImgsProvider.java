package com.xwtec.androidframe.ui.goodDetail.adapterProvider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */
@ItemProviderTag(viewType = GoodDetailAdapter.IMGS_TYPE, layout = R.layout.good_detail_imgs)
public class ImgsProvider extends BaseItemProvider<GoodDetailMultiEntity<GoodDetailResponse.DetailImgTextListBean>, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse.DetailImgTextListBean> data, int position) {
        if (data == null) {
            return;
        }
        List<GoodDetailResponse.DetailImgTextListBean> dataList = data.getDataList();
        RecyclerView recyclerView = helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseQuickAdapter<GoodDetailResponse.DetailImgTextListBean, BaseViewHolder>(R.layout.good_detail_imgs_item, dataList) {
            @Override
            protected void convert(BaseViewHolder helper, GoodDetailResponse.DetailImgTextListBean item) {
                ImageLoadUtil.loadFitWidth(mContext, item.getImgUrl(), (ImageView) helper.getView(R.id.iv), ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(24));
            }
        });
    }

    @Override
    public void onClick(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse.DetailImgTextListBean> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, GoodDetailMultiEntity<GoodDetailResponse.DetailImgTextListBean> data, int position) {
        return false;
    }
}
