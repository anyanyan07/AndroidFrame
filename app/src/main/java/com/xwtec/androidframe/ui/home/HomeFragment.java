package com.xwtec.androidframe.ui.home;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.util.ShopCarAnimUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;


public class HomeFragment extends BaseFragment<HomePresenterImpl> implements HomeContact.HomeView, OnRefreshLoadMoreListener {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.ll_title)
    LinearLayout rlTitle;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv_tab_title)
    RecyclerView rvTabTitle;
    //当前页码
    private int curStartIndex = Constant.FIRST_PAGE_INDEX;
    private int curDefine;
    private HomeAdapter homeAdapter;
    private HomeMultiEntity<BannerBean> bannerDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_BANNER_TYPE);
    private HomeMultiEntity<TabBean> tabDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_TITLE_TYPE);
    private HomeMultiEntity<GoodListBean> goodsDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_CONTENT_TYPE);
    private List<GoodListBean> contentData;
    private List<TabBean> tabBeanList;
    private BaseQuickAdapter<TabBean, BaseViewHolder> tabAdapter;
    private int scrollY;
    private int selectedPosition;
    private int tabScrollX;

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void init() {
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

        rvTabTitle.setVisibility(View.GONE);
        rvTabTitle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rvTabTitle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                tabScrollX += dx;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) {
                    homeAdapter.scrollTab(tabScrollX);
                    tabScrollX = 0;
                }
            }
        });
        tabAdapter = new BaseQuickAdapter<TabBean, BaseViewHolder>(R.layout.home_title_layout) {
            @Override
            protected void convert(BaseViewHolder helper, TabBean tabBean) {
                helper.setText(R.id.tv_title, tabBean.getDefineName());
                helper.getView(R.id.item_title).setSelected(selectedPosition == helper.getAdapterPosition());
            }
        };
        tabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == selectedPosition) {
                    return;
                }
                updateTab(position);
                homeAdapter.updateTab(position);
                fetchGoodsData(tabBeanList.get(position).getId(), 0);
            }
        });
        rvTabTitle.setAdapter(tabAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollY += dy;
                int titleHeight = ConvertUtils.dp2px(44);
                if (scrollY <= 0) {
                    rlTitle.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (scrollY > 0 && scrollY <= titleHeight) {
                    if (recyclerView.getChildAt(0).getY() == 0) {
                        rlTitle.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    } else {
                        float scale = (float) scrollY / titleHeight;
                        int alpha = Math.round(255 * scale);
                        rlTitle.setBackgroundColor(Color.argb(alpha, 46, 177, 103));
                    }
                } else {
                    rlTitle.setBackgroundColor(Color.argb(255, 46, 177, 103));
                }
                int bannerHeight = ConvertUtils.dp2px(150);
                View banner = recyclerView.getChildAt(0);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int top = banner.getTop();
                if (firstVisibleItemPosition == 0 && Math.abs(top) <= bannerHeight - titleHeight) {
                    rvTabTitle.setVisibility(View.GONE);
                } else {
                    rvTabTitle.setVisibility(View.VISIBLE);
                }
            }
        });
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
            smartRefreshLayout.setNoMoreData(false);
        } else {
            smartRefreshLayout.finishLoadMore(true);
            this.contentData.addAll(goodListBeanList);
        }
        if (resultSize < Constant.PER_PAGE_NUM) {
            smartRefreshLayout.setNoMoreData(true);
        }
        curStartIndex = curStartIndex + resultSize;
        homeAdapter.updateGoodContent();
        if (rvTabTitle.getVisibility() == View.VISIBLE) {
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(1, ConvertUtils.dp2px(44));
        }
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
        this.tabBeanList = tabBeanList;
        tabDataEntity.setData(tabBeanList);
        homeAdapter.setTabData();
        tabAdapter.setNewData(tabBeanList);
        TabBean tabBean = tabBeanList.get(0);
        if (tabBean != null) {
            fetchGoodsData(tabBean.getId(), curStartIndex);
        }
    }

    /**
     * 加入购物车
     */
    public void addShopCart(long goodId, ImageView startView) {
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean == null) {
            ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsId", goodId);
        map.put("goodsNumber", 1);
        map.put("token", userBean.getToken());
        presenter.addShopCart(map, startView);
    }

    @Override
    public void addShopSuccess(ImageView startView) {
        //加入购物车成功，开始动画
        MainActivity mainActivity = (MainActivity) getActivity();
        ShopCarAnimUtil shopCarAnimUtil = new ShopCarAnimUtil(context, mainActivity.getRootView(), mainActivity.getShopCartTabView(), startView);
        shopCarAnimUtil.startAnim();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        fetchGoodsData(curDefine, curStartIndex);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (tabDataEntity.getData() == null || tabDataEntity.getData().isEmpty()) {
            presenter.fetchGoodDefines();
        }
        if (bannerDataEntity.getData() == null || bannerDataEntity.getData().isEmpty()) {
            presenter.getHomeBanner();
        }
        fetchGoodsData(curDefine, Constant.FIRST_PAGE_INDEX);
    }

    public void updateTab(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        tabAdapter.notifyDataSetChanged();
    }

    public void scrollTab(int scrollX) {
        if (scrollX != -1 && rvTabTitle != null) {
            rvTabTitle.scrollBy(scrollX, 0);
            tabScrollX = 0;
        }
    }


    @OnClick({R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                ARouter.getInstance().build(Constant.CONTACT_SERVICE_ROUTER)
                        .navigation();
                break;
            default:
                break;
        }
    }
}
