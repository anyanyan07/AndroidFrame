package com.xwtec.androidframe.ui.mine;


import android.support.v4.app.Fragment;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<MinePresenterImpl> implements MineContact.MineView{

    @Inject
    public MineFragment() {
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
