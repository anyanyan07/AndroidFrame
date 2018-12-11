package com.xwtec.androidframe.ui.comment;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author ayy
 * @Date 2018/10/11.
 * Describe:xxx
 */

public class CommentPresenterImpl extends BasePresenter<CommentContact.CommentView> implements CommentContact.CommentPresenter {
   private NetResourceRepo netResourceRepo;

    @Inject
    public CommentPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void comment(Map<String, RequestBody> map, List<MultipartBody.Part> parts) {
        netResourceRepo.submitComment(map,parts)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()){
                                view.commentSuccess();
                            }else{
                                view.commentFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (view != null) {
                            view.commentFail("评论失败，请稍后重试");
                        }
                    }
                });

    }
}
