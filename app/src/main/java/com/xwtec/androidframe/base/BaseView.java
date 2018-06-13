package com.xwtec.androidframe.base;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface BaseView {
    /**
     * 请求加载对话框
     */
    void showLoading();

    /**
     * 取消对话框
     */
    void dismissLoading();

    /**
     * 显示加载失败
     */
    void showLoadFail(String msg);

    /**
     * 请求数据失败
     * @param errorMsg
     */
    void fail(String errorMsg);

}
