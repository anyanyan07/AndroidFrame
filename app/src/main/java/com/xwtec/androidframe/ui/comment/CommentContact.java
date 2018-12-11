package com.xwtec.androidframe.ui.comment;

import com.xwtec.androidframe.base.BaseView;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author ayy
 * @Date 2018/10/11.
 * Describe:xxx
 */

public interface CommentContact {
    interface CommentView extends BaseView{
        void commentSuccess();
        void commentFail(String msg);
    }

    interface CommentPresenter{
        void comment(Map<String, RequestBody> map, List<MultipartBody.Part> parts);
    }
}
