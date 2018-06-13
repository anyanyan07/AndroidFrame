package com.xwtec.androidframe.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by ayy on 2018/6/13.
 * Describe:加载图片工具类
 */

public class ImageLoadUtil {

    public static void load(Context context, Object path, @NonNull ImageView imageView) {
        if (path==null)
            return;
        Glide.with(context).load(path).into(imageView);
    }

    public static void loadFitCenter(Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url))
            return;
        Glide.with(context).load(url).apply(new RequestOptions().fitCenter()).into(imageView);
    }

    public static void loadCenterCrop(Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url))
            return;
        Glide.with(context).load(url).apply(new RequestOptions().centerCrop()).into(imageView);
    }
}
