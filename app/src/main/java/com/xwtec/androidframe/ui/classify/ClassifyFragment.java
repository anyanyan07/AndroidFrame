package com.xwtec.androidframe.ui.classify;


import android.support.v4.app.Fragment;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenterImpl> implements ClassifyContact.ClassifyView {

    @Inject
    public ClassifyFragment() {
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

}
