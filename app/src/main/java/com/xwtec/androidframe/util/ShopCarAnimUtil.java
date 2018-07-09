package com.xwtec.androidframe.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xwtec.androidframe.R;

/**
 * Created by Administrator on 2018/4/18.
 * 加入购物车动画效果
 */

public class ShopCarAnimUtil {
    private Context mContext;
    //父布局，为RelativeLayout或者FrameLayout
    private ViewGroup rootView;
    private View targetView;
    private ImageView startView;
    private int animWidth = 100;
    private int animHeight = 100;
    private PathMeasure pathMeasure;
    private ImageView animView;
    private float[] currentPos = new float[2];
    private ValueAnimator valueAnimator;


    public ShopCarAnimUtil(Context mContext, ViewGroup rootView, View targetView, ImageView startView) {
        this.mContext = mContext;
        this.rootView = rootView;
        this.targetView = targetView;
        this.startView = startView;
    }

    public ShopCarAnimUtil setSize(int animWidth, int animHeight) {
        this.animWidth = animWidth;
        this.animHeight = animHeight;
        return this;
    }

    private void calculatePos() {
        if (rootView instanceof RelativeLayout || rootView instanceof FrameLayout) {

        } else {
            throw new RuntimeException("the rootView must be RelativeLayout or FrameLayout!");
        }
        animView = new ImageView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(animWidth, animHeight);
        rootView.addView(animView, layoutParams);
        animView.setImageResource(R.mipmap.ic_launcher);
//        animView.setImageDrawable(animSourceView.getDrawable());
        final int[] rootLoc = new int[2];
        rootView.getLocationInWindow(rootLoc);

        int[] startLoc = new int[2];
        startView.getLocationInWindow(startLoc);

        int[] targetLoc = new int[2];
        targetView.getLocationInWindow(targetLoc);

        //计算贝塞尔曲线
        int startX = startLoc[0] - rootLoc[0] + startView.getWidth() / 2;
        int startY = startLoc[1] - rootLoc[1] + startView.getHeight() / 2;

        int endX = targetLoc[0] - rootLoc[0] + targetView.getWidth() / 3;
        int endY = targetLoc[1] - rootLoc[1];

        int quadX = (startX + endX) / 2;
        int quadY = startY;

        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo(quadX, quadY, endX, endY);
        pathMeasure = new PathMeasure(path, false);
    }

    public void startAnim() {
        calculatePos();
        valueAnimator = ObjectAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                pathMeasure.getPosTan(distance, currentPos, null);
                animView.setTranslationX(currentPos[0]);
                animView.setTranslationY(currentPos[1]);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (rootView != null && animView != null) {
                    rootView.removeView(animView);
                    animView = null;
                }
            }
        });
        valueAnimator.start();

    }

    public void stopAnim() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
            valueAnimator = null;
            mContext = null;
        }
    }
}
