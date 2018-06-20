package com.xwtec.androidframe.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.AppManager;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.classify.ClassifyFragment;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.mine.MineFragment;
import com.xwtec.androidframe.ui.shopCart.ShopCartFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.MAIN_ROUTER)
public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContact.MainView {

    @Inject
    HomeFragment homeFragment;

    @Inject
    ClassifyFragment classifyFragment;

    @Inject
    ShopCartFragment shopCartFragment;

    @Inject
    MineFragment mineFragment;

    @BindView(R.id.tab_home)
    TextView tabHome;
    @BindView(R.id.tab_classify)
    TextView tabClassify;
    @BindView(R.id.tab_shop_cart)
    TextView tabShopCart;
    @BindView(R.id.tab_mine)
    TextView tabMine;
    private long lastBackTime;

    @Override
    protected void init() {
        setCurFragment(tabHome, homeFragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.tab_home, R.id.tab_classify, R.id.tab_shop_cart, R.id.tab_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                setCurFragment(tabHome, homeFragment);
                break;
            case R.id.tab_classify:
                setCurFragment(tabClassify, classifyFragment);
                break;
            case R.id.tab_shop_cart:
                setCurFragment(tabShopCart, shopCartFragment);
                break;
            case R.id.tab_mine:
                setCurFragment(tabMine, mineFragment);
                break;
        }
    }

    private void setCurFragment(TextView tab, Fragment targetFragment) {
        if (tabHome.isSelected()) fromTab = "home";
        if (tabClassify.isSelected()) fromTab = "classify";
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        setAllTabsUnselected();
        hideAllFragments(fragmentTransaction);
        tab.setSelected(true);
        if (targetFragment.isAdded()) {
            fragmentTransaction.show(targetFragment);
        } else {
            fragmentTransaction.add(R.id.frameLayout, targetFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setAllTabsUnselected() {
        tabHome.setSelected(false);
        tabClassify.setSelected(false);
        tabShopCart.setSelected(false);
        tabMine.setSelected(false);
    }

    private void hideAllFragments(FragmentTransaction fragmentTransaction) {
        if (homeFragment.isAdded()) {
            fragmentTransaction.hide(homeFragment);
        }
        if (classifyFragment.isAdded()) {
            fragmentTransaction.hide(classifyFragment);
        }
        if (shopCartFragment.isAdded()) {
            fragmentTransaction.hide(shopCartFragment);
        }
        if (mineFragment.isAdded()) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    private String fromTab;

    public void goBackTab() {
        if ("home".equals(fromTab)) {
            setCurFragment(tabHome, homeFragment);
        } else if ("classify".equals(fromTab)) {
            setCurFragment(tabClassify, classifyFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBackTime <= 2000) {
            AppManager.get().finishAllActivity();
            System.exit(0);
            super.onBackPressed();
        } else {
            lastBackTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次退出");
        }
    }
}
