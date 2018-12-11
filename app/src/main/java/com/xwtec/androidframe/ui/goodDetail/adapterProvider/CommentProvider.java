package com.xwtec.androidframe.ui.goodDetail.adapterProvider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.widget.GridView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.goodDetail.CommentImgsAdapter;
import com.xwtec.androidframe.ui.goodDetail.GoodDetailAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */
@ItemProviderTag(viewType = GoodDetailAdapter.COMMENT_TYPE, layout = R.layout.home_good_content)
public class CommentProvider extends BaseItemProvider<GoodDetailMultiEntity<CommentInfo>, BaseViewHolder> {
    private long goodId;

    public CommentProvider(long goodId) {
        this.goodId = goodId;
    }

    @Override
    public void convert(BaseViewHolder helper, GoodDetailMultiEntity<CommentInfo> data, int position) {
        if (data != null) {
            List<CommentInfo> commentInfoList = data.getDataList();
            if (commentInfoList == null || commentInfoList.size() == 0) {
                return;
            }
            RecyclerView recyclerView = helper.getView(R.id.rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            BaseQuickAdapter<CommentInfo, BaseViewHolder> adapter = new BaseQuickAdapter<CommentInfo, BaseViewHolder>(R.layout.comment_item, commentInfoList) {
                @Override
                protected void convert(BaseViewHolder helper, CommentInfo item) {
                    ImageView ivHeader = helper.getView(R.id.iv_header);
                    ImageLoadUtil.loadCircleImage(mContext, item.getHeadImg(), ivHeader);
                    helper.setText(R.id.tv_name, item.getNickName());
                    helper.setText(R.id.tv_date, DateFormat.format("yyyy-MM-dd", item.getCommentDate()));
                    helper.setText(R.id.tv_comment, item.getComment());
                    List<String> imgUrlList = item.getImgUrlList();
                    if (imgUrlList == null || imgUrlList.size() == 0) {
                        return;
                    }
                    GridView gvImgs = helper.getView(R.id.gv_imgs);
                    gvImgs.setAdapter(new CommentImgsAdapter(mContext, imgUrlList,goodId));
                }
            };
//            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    ARouter.getInstance().build(Constant.ALL_COMMENT_ROUTER)
//                            .withLong("goodId", goodId).navigation();
//                }
//            });
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(BaseViewHolder helper, GoodDetailMultiEntity<CommentInfo> data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, GoodDetailMultiEntity<CommentInfo> data, int position) {
        return false;
    }
}
