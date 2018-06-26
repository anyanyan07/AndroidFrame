package com.xwtec.androidframe.ui.classify;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.classify.bean.CategoryBean;

import java.util.List;

/**
 * Created by ayy on 2018/5/28.
 * Describe:xxx
 */

public interface ClassifyContact {
    interface ClassifyView extends BaseView {
        void fetchCategoriesSuccess(List<CategoryBean> data);
    }

    interface ClassifyPresenter {
        void fetchCategories();
    }
}
