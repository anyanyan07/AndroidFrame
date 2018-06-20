package com.xwtec.androidframe.ui.home.adapterProvider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.home.HomeAdapter;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;
import com.xwtec.androidframe.ui.home.bean.TabBean;

import java.util.List;

/**
 * Created by ayy on 2018/6/17.
 * Describe:商品定义分类
 */
@ItemProviderTag(viewType = HomeAdapter.HOME_TITLE_TYPE, layout = R.layout.tab_title)
public class TabTitleProvider extends BaseItemProvider<HomeMultiEntity<TabBean>, BaseViewHolder> {
    private HomeFragment homeFragment;
    private HomeAdapter homeAdapter;
    private int selectedPosition = 0;

    public TabTitleProvider(HomeFragment homeFragment, HomeAdapter homeAdapter) {
        this.homeFragment = homeFragment;
        this.homeAdapter = homeAdapter;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultiEntity<TabBean> data, int position) {
        final List<TabBean> tabBeanList = data.getData();
        RecyclerView recyclerView = helper.getView(R.id.rv_tab_title);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        BaseQuickAdapter adapter = new BaseQuickAdapter<TabBean, BaseViewHolder>(R.layout.home_title_layout, tabBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, TabBean tabBean) {
                helper.setText(R.id.tv_title, tabBean.getDefineName());
                helper.getView(R.id.item_title).setSelected(selectedPosition == helper.getAdapterPosition());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectedPosition = position;
                homeAdapter.updateTab();
                homeFragment.fetchGoodsData(tabBeanList.get(position).getId(), 0);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(BaseViewHolder helper, HomeMultiEntity<TabBean> data, int position) {
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, HomeMultiEntity<TabBean> data, int position) {
        return false;
    }
}
