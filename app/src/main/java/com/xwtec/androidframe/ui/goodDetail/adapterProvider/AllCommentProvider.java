package com.xwtec.androidframe.ui.goodDetail.adapterProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */
@ItemProviderTag(viewType = GoodDetailAdapter.ALL_COMMENT_TYPE, layout = R.layout.all_comment)
public class AllCommentProvider extends BaseItemProvider<GoodDetailMultiEntity, BaseViewHolder> {
    private long goodId;

    public AllCommentProvider(long goodId) {
        this.goodId = goodId;
    }

    @Override
    public void convert(BaseViewHolder helper, GoodDetailMultiEntity data, int position) {

    }

    @Override
    public void onClick(BaseViewHolder helper, GoodDetailMultiEntity data, int position) {
        ARouter.getInstance().build(Constant.ALL_COMMENT_ROUTER)
                .withLong("goodId", goodId).navigation();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, GoodDetailMultiEntity data, int position) {
        return false;
    }
}
