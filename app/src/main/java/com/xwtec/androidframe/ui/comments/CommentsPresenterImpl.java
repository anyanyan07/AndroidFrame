package com.xwtec.androidframe.ui.comments;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */

public class CommentsPresenterImpl extends BasePresenter<CommentsContact.CommentsView> implements CommentsContact.CommentsPresenter{

    private NetResourceRepo netResourceRepo;

    @Inject
    public CommentsPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void fetchComments(long goodId, int commentLevel, int startIndex, int showNum) {
        netResourceRepo.fetchGoodComment(goodId,commentLevel,startIndex,showNum)
                .subscribe(new ResponseObserver<BaseResponse<List<CommentInfo>>>(this) {
                    @Override
                    public void onNext(BaseResponse<List<CommentInfo>> baseResponse) {
                        if (view != null){
                            if (baseResponse.isSuccess()){
                                view.fetchCommentsSuccess(baseResponse.getContent());
                            }
                        }
                    }
                });
    }
}
