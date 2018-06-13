package com.xwtec.androidframe.ui.home;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment {

    @Inject
    public HomeFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


}
