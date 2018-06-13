package com.xwtec.androidframe.base.baseadapter;

import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xwtec.androidframe.interfaces.AdapterInnerViewClickListener;
import com.xwtec.androidframe.interfaces.RVItemClickListener;
import com.xwtec.androidframe.interfaces.RVItemLongClickListener;
import com.xwtec.androidframe.util.ImageLoadUtil;


/**
 * Created by zhujie on 2018-2-8.
 * 通用holder
 */

public class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private RVItemClickListener mListener;
    private RVItemLongClickListener mLongClickListener;
    private SparseArray<View> mViews;
    private int delayTime = -1;

    public BaseHolder(View itemView, RVItemClickListener listener, RVItemLongClickListener longClickListener) {
        super(itemView);
        mViews = new SparseArray<View>();
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public BaseHolder(View itemView, RVItemClickListener listener, RVItemLongClickListener longClickListener, int delayTime) {
        super(itemView);
        mViews = new SparseArray<View>();
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.delayTime = delayTime;
    }

    //通过viewId获取控件
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }

    /**
     * 设置TextView的值
     */
    public BaseHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    public BaseHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public BaseHolder setImageBitamp(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public BaseHolder setImageURI(int viewId, String uri) {
        ImageView view = getView(viewId);
        ImageLoadUtil.load(view.getContext(), uri,
                view);
        return this;
    }

    public BaseHolder setImageURIFitCenter(int viewId, String uri) {
        ImageView view = getView(viewId);
        ImageLoadUtil.loadFitCenter(view.getContext(), uri,
                view);
        return this;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view, getLayoutPosition());
        }
    }


    @Override
    public boolean onLongClick(View view) {
        if (mLongClickListener != null) {
            mLongClickListener.onItemLongClick(view, getLayoutPosition());
        }
        return true;
    }

    public void setViewClickListener(@IdRes int viewId, final AdapterInnerViewClickListener listener){
        getView(viewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.viewClick(view, getLayoutPosition());
                }
            }
        });
    }
}
