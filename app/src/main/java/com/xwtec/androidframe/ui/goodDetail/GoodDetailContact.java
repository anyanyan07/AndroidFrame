package com.xwtec.androidframe.ui.goodDetail;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ayy on 2018/6/18.
 * Describe:xxx
 */

public interface GoodDetailContact {
    interface GoodDetailView extends BaseView {
        void fetchGoodDetailSuccess(GoodDetailResponse goodDetailResponse);

        void addShopCartSuccess();

        void fetchCommentSuccess(List<CommentInfo> commentInfoList);
    }

    interface GoodDetailPresenter {
        void fetchGoodDetail(long goodId);

        /**
         * 加入购物车
         */
        void addShopCart(HashMap<String, Object> map);

        void fetchComment(long goodId,int commentLevel,int startIndex,int showNum);
    }
}
