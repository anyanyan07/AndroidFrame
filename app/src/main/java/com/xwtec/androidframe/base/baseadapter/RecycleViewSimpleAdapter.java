package com.xwtec.androidframe.base.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xwtec.androidframe.interfaces.RVItemClickListener;
import com.xwtec.androidframe.interfaces.RVItemLongClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhujie on 2018-2-8.
 * 通用适配器
 */
public abstract class RecycleViewSimpleAdapter<T> extends RecyclerView.Adapter<BaseHolder> {
    private RVItemClickListener mListener;
    private RVItemLongClickListener mLongClickListener;
    private Context mcontext;
    private LayoutInflater mInflater;
    private List<T> mItemList = new ArrayList<>();
    private int mlayoutId;
    private int choosePosion = -1;//选中状态
    private boolean isRadio = false;//单选
    private boolean isAll = false;//全选
    // 用来控制选中状况
    private static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
    private int delayedTime = -1;

    public RecycleViewSimpleAdapter(Context context, int layoutId) {
        this.mcontext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mlayoutId = layoutId;
    }

    public RecycleViewSimpleAdapter(Context context, int layoutId, int delayedTime) {
        this.mcontext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mlayoutId = layoutId;
        this.delayedTime = delayedTime;
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(delayedTime!=-1){
            return new BaseHolder(mInflater.inflate(mlayoutId, parent, false), mListener, mLongClickListener,delayedTime);
        }
        return new BaseHolder(mInflater.inflate(mlayoutId, parent, false), mListener, mLongClickListener);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        bindData(holder, mItemList.get(position));
        if (isRadio) {
            if (position == choosePosion) {
                holder.itemView.setSelected(true);
            } else {
                holder.itemView.setSelected(false);
            }
        } else {
            if (null != getIsSelected().get(position))
                holder.itemView.setSelected(getIsSelected().get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mItemList == null)
            return 0;
        return mItemList.size();
    }

    public void setSelectItem(int selectItem) {
        this.choosePosion = selectItem;
    }

    public int getSelectItem() {
        return choosePosion;
    }

    public Context getContext() {
        return mcontext;
    }

    public boolean isRadio() {
        return isRadio;
    }

    public void setRadio(boolean radio) {
        isRadio = radio;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        RecycleViewSimpleAdapter.isSelected = isSelected;
    }

    public void setData(List<T> list) {
        this.mItemList = list;
        notifyDataSetChanged();
    }

    public void clearDataList() {
        if (null != mItemList) {
            mItemList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(RVItemClickListener listener) {
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(RVItemLongClickListener listener) {

        this.mLongClickListener = listener;
    }

    protected abstract void bindData(BaseHolder holder, T t);

}
