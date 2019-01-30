package com.xwtec.androidframe.ui.comments;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * @Author ayy
 * @Date 2018/11/1.
 * Describe:xxx
 */

public class PictureAdapter extends PagerAdapter {

    private List<String> imgUrls;
    private Context context;

    public PictureAdapter(Context context) {
        this.context = context;
    }

    public void setNewData(List<String> imgUrls){
        this.imgUrls = imgUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imgUrls == null ? 0 : imgUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        container.addView(imageView);
        ImageLoadUtil.loadFitCenter(context,imgUrls.get(position),imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
