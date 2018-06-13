package com.xwtec.androidframe.ui.classify;


import android.support.v4.app.Fragment;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment {

    @Inject
    public ClassifyFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

}
