package com.xwtec.androidframe.ui.classify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
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
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.GridSpacingItemDecoration;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.CLASSIFY_LIST_ROUTER)
public class ClassifyListActivity extends BaseActivity<ClassifyListPresenterImpl> implements ClassifyListContact.ClassifyListView, OnRefreshLoadMoreListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private int id;
    private int curIndex = 0;
    private BaseQuickAdapter<CategoryContentBean, BaseViewHolder> adapter;
    private List<CategoryContentBean> data = new ArrayList<>();

    @Override
    protected void init() {
        super.init();
        refreshLayout.setOnRefreshLoadMoreListener(this);
        initRv();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        tvTitle.setText(intent.getStringExtra("name"));
        fetchContent(id, curIndex);
    }

    private void initRv() {
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.addItemDecoration(new GridSpacingItemDecoration(2, ConvertUtils.dp2px(8), true));
        adapter = new BaseQuickAdapter<CategoryContentBean, BaseViewHolder>(R.layout.home_content_layout, data) {
            @Override
            protected void convert(BaseViewHolder helper, final CategoryContentBean item) {
                ImageLoadUtil.loadFitCenter(mContext, item.getImgUrl(), (ImageView) helper.getView(R.id.iv_good));
                helper.setText(R.id.tv_good_name, item.getTitle());
                helper.setText(R.id.tv_good_num, item.getIntroduction());
                PriceView priceView = helper.getView(R.id.tv_cur_price);
                priceView.setPrice(item.getDiscountPrice());
                helper.setText(R.id.tv_old_price, item.getOriginalPrice());
                ImageView ivShopCart = helper.getView(R.id.iv_shop_cart);
                ivShopCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addShopCart(item.getId());
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(Constant.GOODS_DETAIL_ROUTER)
                        .withLong("goodId", data.get(position).getId())
                        .navigation();
            }
        });
        adapter.setEmptyView(R.layout.empty_layout, refreshLayout);
        rv.setAdapter(adapter);
    }

    protected void fetchContent(int categoryId, int startIndex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("categorId", categoryId);
        map.put("startIndex", startIndex);
        map.put("showNumber", Constant.PER_PAGE_NUM);
        presenter.fetchCategoryContent(map);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_classify_list;
    }

    @Override
    public void fetchContentSuccess(List<CategoryContentBean> categoryContentList) {
        if (curIndex == 0) {
            refreshLayout.finishRefresh(true);
            data.clear();
        } else {
            if (categoryContentList.size() < Constant.PER_PAGE_NUM) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                refreshLayout.finishLoadMore(true);
            }
        }
        data.addAll(categoryContentList);
        adapter.notifyDataSetChanged();
        curIndex += categoryContentList.size();
    }

    @Override
    public void showLoadFail(String msg) {
        super.showLoadFail(msg);
        if (curIndex == 0) {
            refreshLayout.finishRefresh(false);
        } else {
            refreshLayout.finishLoadMore(false);
        }
    }

    @OnClick(R.id.iv_left)
    public void onClick() {
        finish();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        fetchContent(id, curIndex);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        curIndex = 0;
        fetchContent(id, curIndex);
    }

    /**
     * 加入购物车
     */
    public void addShopCart(long goodId) {
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean == null) {
            ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsId", goodId);
        map.put("goodsNumber", 1);
        map.put("token", userBean.getToken());
        presenter.addShopCart(map);
    }

    @Override
    public void addShopCartSuccess() {
        ToastUtils.showShort("加入购物车成功");
    }
}
