package com.xwtec.androidframe.ui.classify;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.base.baseadapter.BaseHolder;
import com.xwtec.androidframe.base.baseadapter.RecycleViewSimpleAdapter;
import com.xwtec.androidframe.interfaces.RVItemClickListener;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenterImpl> implements ClassifyContact.ClassifyView {

    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private List<CategoryBean> categoryList;
    private List<CategoryContentBean> categoryContentList;
    private RecycleViewSimpleAdapter<CategoryContentBean> categoryContentAdapter;
    private RecycleViewSimpleAdapter<CategoryBean> categoryAdapter;

    @Inject
    public ClassifyFragment() {
    }

    @Override
    protected void init() {
        initCategory();
        initContent();
        presenter.fetchCategories();
    }

    private void initCategory() {
        rvCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        categoryAdapter = new RecycleViewSimpleAdapter<CategoryBean>(context, R.layout.category_item) {
            @Override
            protected void bindData(BaseHolder holder, CategoryBean categoryBean) {
                holder.setText(R.id.tv_category_name, categoryBean.getName());
            }
        };
        categoryAdapter.setRadio(true);
        categoryAdapter.setOnItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                categoryAdapter.setSelectItem(position);
                CategoryBean categoryBean = categoryList.get(position);
                fetchContent(categoryBean.getId(), 0, 20);
            }
        });
        rvCategory.setAdapter(categoryAdapter);
    }

    private void initContent() {
        rvContent.setLayoutManager(new GridLayoutManager(context,2));
        categoryContentAdapter = new RecycleViewSimpleAdapter<CategoryContentBean>(context, R.layout.category_content_item) {
            @Override
            protected void bindData(BaseHolder holder, CategoryContentBean categoryContentBean) {
                holder.setImageURIFitCenter(R.id.iv_pic, categoryContentBean.getImgUrl());
                holder.setText(R.id.tv_name, categoryContentBean.getTitle());
            }
        };
        categoryContentAdapter.setOnItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }
        });
        rvContent.setAdapter(categoryContentAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    protected void fetchContent(int categoryId, int startIndex, int showNum) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("categorId", categoryId);
        map.put("startIndex", startIndex);
        map.put("showNumber", showNum);
        presenter.fetchCategoryContent(map);
    }

    @Override
    public void fetchCategoriesSuccess(List<CategoryBean> data) {
        categoryList = data;
        categoryAdapter.setData(categoryList);
        if (categoryList != null && categoryList.size() > 0) {
            categoryAdapter.setSelectItem(0);
            fetchContent(categoryList.get(0).getId(), 0, 20);
        }
    }

    @Override
    public void fetchContentSuccess(List<CategoryContentBean> data) {
        categoryContentList = data;
        categoryContentAdapter.setData(categoryContentList);
    }
}
