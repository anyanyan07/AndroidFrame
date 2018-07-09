package com.xwtec.androidframe.ui.splash;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.CircleProgressbar;
import com.xwtec.androidframe.manager.Constant;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashPresenterImpl> {
    @BindView(R.id.circle_progress_bar)
    CircleProgressbar circleProgressbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private int[] startRedIds = {R.mipmap.start01, R.mipmap.start02};

    @Override
    protected void init() {
        super.init();
        circleProgressbar.setCountdownProgressListener(new CircleProgressbar.OnCountdownProgressListener() {
            @Override
            public void onProgress(int progress) {
                if (progress == 0) {
                    goToMain();
                }
            }
        });
        circleProgressbar.start();
//        if ("true".equals(CacheUtils.getInstance().getString(Constant.FIRST_USE))) {
//            viewPager.setVisibility(View.VISIBLE);
//            viewPager.setAdapter(new StartAdapter());
//        }
    }

    private void goToMain() {
        ARouter.getInstance().build(Constant.MAIN_ROUTER).navigation();
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @OnClick({R.id.circle_progress_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle_progress_bar:
                goToMain();
                break;
            default:
                break;
        }
    }

    class StartAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return startRedIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(SplashActivity.this);
            imageView.setImageResource(startRedIds[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        }
    }
}
