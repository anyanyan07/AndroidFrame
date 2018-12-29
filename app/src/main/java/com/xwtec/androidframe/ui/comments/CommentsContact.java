package com.xwtec.androidframe.ui.comments;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;

import java.util.List;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */

public interface CommentsContact {
    interface CommentsView extends BaseView{
        void fetchCommentsSuccess(List<CommentInfo> commentInfoList);
    }
    interface CommentsPresenter{
        void fetchComments(long goodId,int commentLevel,int startIndex,int showNum);
    }
}
