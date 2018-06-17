package com.xwtec.androidframe.ui.classify;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface ClassifyContact {
    interface ClassifyView extends BaseView {
        void fetchCategoriesSuccess(List<CategoryBean> data);

        void fetchContentSuccess(List<CategoryContentBean> data);
    }

    interface ClassifyPresenter {
        void fetchCategories();

        void fetchCategoryContent(HashMap<String, Object> map);
    }
}
