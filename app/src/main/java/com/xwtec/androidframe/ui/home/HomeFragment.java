package com.xwtec.androidframe.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.ui.home.bean.BannerBean;
import com.xwtec.androidframe.ui.home.bean.GoodListBean;
import com.xwtec.androidframe.ui.home.bean.HomeMultiEntity;
import com.xwtec.androidframe.ui.home.bean.TabBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenterImpl> implements HomeContact.HomeView {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_right)
    ImageView ivRight;

    //默认为0;0:推荐商品;1:新品商品;2:热销商品;3:精品礼盒; 4:手工艺品;5:季节商品;6:稀缺商品
    private String[] tabNames = {"推荐", "新品", "热销", "精品", "手工", "季节", "稀缺"};
    private HomeAdapter homeAdapter;
    private HomeMultiEntity<BannerBean> bannerDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_BANNER_TYPE);
    private HomeMultiEntity<TabBean> tabDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_TITLE_TYPE);
    private HomeMultiEntity<GoodListBean> goodsDataEntity = new HomeMultiEntity<>(HomeAdapter.HOME_CONTENT_TYPE);

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void init() {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.xiaoxi);
        initTabData();
        initRv();
        presenter.getHomeBanner();
        fetchGoodsData(0, 0);
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        List<HomeMultiEntity> homeMultiEntityList = new ArrayList<>();
        homeMultiEntityList.add(bannerDataEntity);
        homeMultiEntityList.add(tabDataEntity);
        homeMultiEntityList.add(goodsDataEntity);
        homeAdapter = new HomeAdapter(homeMultiEntityList,this);
        recyclerView.setAdapter(homeAdapter);
    }

    private void initTabData() {
        List<TabBean> tabData = new ArrayList<>();
        for (int i = 0; i < tabNames.length; i++) {
            TabBean tabBean = new TabBean();
            tabBean.setTabDefine(i);
            tabBean.setTabName(tabNames[i]);
            tabData.add(tabBean);
        }
        tabDataEntity.setData(tabData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public void fetchGoodsData(int define, int startIndex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("define", define);
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
        goodsDataEntity.setData(goodListBeanList);
        homeAdapter.updateGoodContent();
    }
}
