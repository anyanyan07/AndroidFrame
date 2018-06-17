package com.xwtec.androidframe.ui.home.adapterProvider;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.home.HomeAdapter;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * Created by ayy on 2018/6/17.
 * Describe:xxx
 */
@ItemProviderTag(viewType = HomeAdapter.HOME_CONTENT_TYPE,layout = R.layout.home_good_content)
public class ContentProvider extends BaseItemProvider<HomeMultiEntity<GoodListBean>,BaseViewHolder> {

    @Override
    public void convert(BaseViewHolder helper, HomeMultiEntity<GoodListBean> data, int position) {
        List<GoodListBean> goodBeanList = data.getData();
        RecyclerView recyclerView =  helper.getView(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(new BaseQuickAdapter<GoodListBean,BaseViewHolder>(R.layout.home_content_layout,goodBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, GoodListBean goodListBean) {
                ImageLoadUtil.loadFitCenter(mContext,goodListBean.getImgUrl(), (ImageView) helper.getView(R.id.iv_good));
                helper.setText(R.id.tv_good_name, goodListBean.getTitle());
                helper.setText(R.id.tv_good_num, goodListBean.getIntroduction());
                helper.setText(R.id.tv_cur_price, goodListBean.getDiscountPrice());
                helper.setText(R.id.tv_old_price, goodListBean.getOriginalPrice());
            }
        });
    }

    @Override
    public void onClick(BaseViewHolder helper, HomeMultiEntity<GoodListBean> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, HomeMultiEntity<GoodListBean> data, int position) {
        return false;
    }
}
