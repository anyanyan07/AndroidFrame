package com.xwtec.androidframe.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;
import com.xwtec.androidframe.ui.home.bean.TabBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenterImpl> implements HomeContact.HomeView, OnRefreshLoadMoreListener {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    //当前页码
    private int curStartIndex = Constant.FIRST_PAGE_INDEX;
    private int curDefine;
    private HomeAdapter homeAdapter;
    private HomeMultiEntity<BannerBean> bannerDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_BANNER_TYPE);
    private HomeMultiEntity<TabBean> tabDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_TITLE_TYPE);
    private HomeMultiEntity<GoodListBean> goodsDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_CONTENT_TYPE);
    private List<GoodListBean> contentData;

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void init() {
        ivLeft.setVisibility(View.GONE);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.xiaoxi);
        initRv();
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        presenter.getHomeBanner();
        presenter.fetchGoodDefines();
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        List<HomeMultiEntity> homeMultiEntityList = new ArrayList<>();
        homeMultiEntityList.add(bannerDataEntity);
        homeMultiEntityList.add(tabDataEntity);
        homeMultiEntityList.add(goodsDataEntity);
        homeAdapter = new HomeAdapter(homeMultiEntityList, this);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public void fetchGoodsData(int define, int startIndex) {
        curDefine = define;
        curStartIndex = startIndex;
        HashMap<String, Object> map = new HashMap<>();
        map.put("defineId", define);
        map.put("startIndex", startIndex);
        map.put("showNumber", 20);
        presenter.getGoodList(map);
    }

    @Override
    public void bannerSuccess(List<BannerBean> bannerBeanList) {
        bannerDataEntity.setData(bannerBeanList);
        homeAdapter.updateBanner();
    }

    @Override
    public void goodListSuccess(List<GoodListBean> goodListBeanList) {
        int resultSize = goodListBeanList.size();
        if (curStartIndex == 0) {
            this.contentData = goodListBeanList;
            goodsDataEntity.setData(contentData);
            smartRefreshLayout.finishRefresh(true);
        } else {
            if (resultSize < Constant.PER_PAGE_NUM) {
                smartRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                smartRefreshLayout.finishLoadMore(true);
            }
            this.contentData.addAll(goodListBeanList);
        }
        curStartIndex = curStartIndex + resultSize;
        homeAdapter.updateGoodContent();
    }

    @Override
    public void goodListFail(String msg) {
        ToastUtils.showShort(msg);
        if (curStartIndex == 0) {
            smartRefreshLayout.finishRefresh(false);
        } else {
            smartRefreshLayout.finishLoadMore(false);
        }
    }

    @Override
    public void fetchGoodDefinesSuccess(List<TabBean> tabBeanList) {
        if (tabBeanList == null || tabBeanList.size() <= 0) {
            return;
        }
        tabDataEntity.setData(tabBeanList);
        homeAdapter.updateTab();
        TabBean tabBean = tabBeanList.get(0);
        if (tabBean != null) {
            fetchGoodsData(tabBean.getId(), curStartIndex);
        }
    }

    //加载更多
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        fetchGoodsData(curDefine, curStartIndex);
    }

    //下拉刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        fetchGoodsData(curDefine, Constant.FIRST_PAGE_INDEX);
    }
}
