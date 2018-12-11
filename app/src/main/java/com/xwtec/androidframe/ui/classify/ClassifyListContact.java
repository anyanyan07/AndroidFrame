package com.xwtec.androidframe.ui.classify;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.classify.bean.CategoryContentBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ayy on 2018/6/26.
 * Describe:xxx
 */

public interface ClassifyListContact {
    interface ClassifyListView extends BaseView{
        void fetchContentSuccess(List<CategoryContentBean> categoryContentList);

        void addShopCartSuccess();
    }

    interface ClassifyListPresenter {
        void fetchCategoryContent(HashMap<String, Object> map);

        void addShopCart(HashMap<String,Object> map);
    }
}
