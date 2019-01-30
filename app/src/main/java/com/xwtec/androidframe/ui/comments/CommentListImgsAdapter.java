package com.xwtec.androidframe.ui.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

/**
 * @Author ayy
 * @Date 2018/10/14.
 * Describe:xxx
 */

public class CommentListImgsAdapter extends BaseAdapter {
    private List<String> imgUrlList;
    private LayoutInflater layoutInflater;
    private Context context;
    private long goodId;

    public CommentListImgsAdapter(Context context, List<String> imgUrlList, long goodId) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.imgUrlList = imgUrlList;
        this.goodId = goodId;
    }

    @Override
    public int getCount() {
        return imgUrlList == null ? 0 : imgUrlList.size();
    }

    @Override
    public Object getItem(int position) {
        return imgUrlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.comment_img_item, parent, false);
        ImageView iv = convertView.findViewById(R.id.iv);
        ImageLoadUtil.loadFitCenter(context, imgUrlList.get(position), iv);
        return convertView;
    }
}
