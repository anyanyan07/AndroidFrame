package com.xwtec.androidframe.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.util.glide.GlideCircleTransform;

import java.io.File;

/**
 * Created by ayy on 2018/6/13.
 * Describe:加载图片工具类
 */

public class ImageLoadUtil {

    public static void load(Context context, Object path, @NonNull ImageView imageView) {
        if (path == null)
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

    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions();
        GlideCircleTransform glideCircleTransform = new GlideCircleTransform(context);
        requestOptions = requestOptions.transform(glideCircleTransform)
                .placeholder(R.mipmap.header_default)
                .error(R.mipmap.header_default);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void loadCircleImageFromFile(Context context, File file, ImageView imageView) {
        if (!file.exists()) {
            return;
        }
        RequestOptions requestOptions = new RequestOptions();
        GlideCircleTransform glideCircleTransform = new GlideCircleTransform(context);
        requestOptions = requestOptions.transform(glideCircleTransform).error(R.mipmap.header_default)
                .placeholder(R.mipmap.header_default)
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context).load(file).apply(requestOptions).into(imageView);
    }

    //图片宽度一定，高按比列进行缩放
    public static void loadFitWidth(final Context context, Object path, final ImageView imageView, final int width) {
        Glide.with(context).asBitmap().load(path).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int imageWidth = resource.getWidth();
                int imageHeight = resource.getHeight();
                int height = width * imageHeight / imageWidth;
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = height;
                layoutParams.width = width;
                imageView.setLayoutParams(layoutParams);
                imageView.setImageBitmap(resource);
            }
        });
    }
}
