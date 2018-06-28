package com.xwtec.androidframe.ui.classify;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.util.GridSpacingItemDecoration;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenterImpl> implements ClassifyContact.ClassifyView, OnRefreshListener {

    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private List<CategoryBean> categoryList;
    private BaseQuickAdapter<CategoryBean, BaseViewHolder> categoryAdapter;

    @Inject
    public ClassifyFragment() {
    }

    @Override
    protected void init() {
        ivLeft.setVisibility(View.GONE);
        tvTitle.setText(R.string.classify);
        initContent();
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        presenter.fetchCategories();
    }

    private void initContent() {
        rvCategory.setLayoutManager(new GridLayoutManager(context, 3));
        rvCategory.addItemDecoration(new GridSpacingItemDecoration(3, ConvertUtils.dp2px(8), true));
        categoryAdapter = new BaseQuickAdapter<CategoryBean, BaseViewHolder>(R.layout.category_content_item, categoryList) {
            @Override
            protected void convert(BaseViewHolder helper, CategoryBean item) {
                ImageLoadUtil.loadFitCenter(mContext, item.getImgUrl(), (ImageView) helper.getView(R.id.iv_pic));
                helper.setText(R.id.tv_name, item.getName());
            }
        };
        categoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到分类列表页
                ARouter.getInstance().build(Constant.CLASSIFY_LIST_ROUTER)
                        .withInt("id", categoryList.get(position).getId())
                        .withString("name", categoryList.get(position).getName())
                        .navigation();
            }
        });
        rvCategory.setAdapter(categoryAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void fetchCategoriesSuccess(List<CategoryBean> data) {
        refreshLayout.finishRefresh(true);
        categoryList = data;
        categoryAdapter.setNewData(categoryList);
    }

    @Override
    public void showLoadFail(String msg) {
        super.showLoadFail(msg);
        refreshLayout.finishRefresh(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.fetchCategories();
    }
}
