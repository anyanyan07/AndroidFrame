package com.xwtec.androidframe.ui.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.AppManager;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.manager.intercepter.LoginIntercepter;
import com.xwtec.androidframe.ui.classify.ClassifyFragment;
import com.xwtec.androidframe.ui.home.HomeFragment;
import com.xwtec.androidframe.ui.mine.MineFragment;
import com.xwtec.androidframe.ui.shopCart.ShopCartFragment;
import com.xwtec.androidframe.util.DialogUtils;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@Route(path = Constant.MAIN_ROUTER)
@RuntimePermissions
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
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    private long lastBackTime;

    @Override
    protected void init() {
        MainActivityPermissionsDispatcher.mustPermissionWithPermissionCheck(this);
        setCurFragment(tabHome, homeFragment);
        initRxBus();
    }

    private void initRxBus() {
        RxBus.getInstance().register(RxBusMSG.class, new Consumer<RxBusMSG>() {
            @Override
            public void accept(RxBusMSG rxBusMSG) throws Exception {
                switch (rxBusMSG.getCode()) {
                    case Constant.RX_GO_SHOP_CART:
                        if (!LoginIntercepter.intercepter(Constant.SHOP_CART_FRAG)) {
                            setCurFragment(tabShopCart, shopCartFragment);
                        }
                        break;
                    case Constant.SHOP_CART_FRAG:
                        setCurFragment(tabShopCart, shopCartFragment);
                        break;
                    case Constant.MINE_FRAG:
                        setCurFragment(tabMine, mineFragment);
                        break;
                    default:
                        break;
                }
            }
        });
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
                if (!LoginIntercepter.intercepter(Constant.SHOP_CART_FRAG)) {
                    setCurFragment(tabShopCart, shopCartFragment);
                }
                break;
            case R.id.tab_mine:
                if (!LoginIntercepter.intercepter(Constant.MINE_FRAG)) {
                    setCurFragment(tabMine, mineFragment);
                }
                break;
            default:
                break;
        }
    }

    private void setCurFragment(TextView tab, Fragment targetFragment) {
        if (tabHome.isSelected()) {
            fromTab = "home";
        }
        if (tabClassify.isSelected()) {
            fromTab = "classify";
        }
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

    public ViewGroup getRootView() {
        return rlMain;
    }

    public View getShopCartTabView() {
        return tabShopCart;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBackTime <= 2000) {
            AppManager.getInstance().finishAllActivity();
            System.exit(0);
            super.onBackPressed();
        } else {
            lastBackTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次退出");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @NeedsPermission({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void mustPermission() {

    }

    @OnPermissionDenied({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void noPermission() {
        DialogUtils.showDeniedDialog(this, "拒绝此权限将无法使用我们的应用，请授权给我们", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivityPermissionsDispatcher.mustPermissionWithPermissionCheck(MainActivity.this);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityUtils.finishAllActivities();
                System.exit(0);
            }
        });
    }

    @OnNeverAskAgain({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void neverAsk() {
        DialogUtils.showSettingPermissionDialog(this, this, "拒绝此权限将无法使用我们的应用，请授权给我们", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityUtils.finishAllActivities();
                System.exit(0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.SETTING_MUST_PERMISSION_CODE) {
            MainActivityPermissionsDispatcher.mustPermissionWithPermissionCheck(this);
        }
    }
}
