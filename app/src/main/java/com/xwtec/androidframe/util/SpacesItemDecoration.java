package com.xwtec.androidframe.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhujie on 2017/11/8.
 * RecycleView设置间距
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private String type;

    /**
     * @param type
     * @param space 设置不同方向的间隔
     */
    public SpacesItemDecoration(String type, int space) {
        this.space = space;
        this.type = type;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildPosition(view) == 0) {
        if ("".equals(type)) {
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = 0;
            } else {
                outRect.top = space;
            }
        } else {
            if ("top".equals(type)) {
                outRect.top = space;
                outRect.left = 0;
                outRect.right = 0;
                outRect.bottom = 0;
            } else if ("bottom".equals(type)) {
                outRect.bottom = space;
                outRect.top = 0;
                outRect.left = 0;
                outRect.right = 0;
            } else if ("left".equals(type)) {
                outRect.left = space;
                outRect.top = 0;
                outRect.right = 0;
                outRect.bottom = 0;
            } else if ("right".equals(type)) {
                outRect.right = space;
                outRect.top = 0;
                outRect.left = 0;
                outRect.bottom = 0;
            } else if ("leftright".equals(type)) {
                outRect.right = space;
                outRect.top = 0;
                outRect.left = space;
                outRect.bottom = 0;
            } else if ("all".equals(type)) {
                outRect.right = space;
                outRect.top = space;
                outRect.left = space;
                outRect.bottom = space;
            }
        }
    }
}


