package com.xwtec.androidframe.ui.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseFragment;
import com.xwtec.androidframe.base.baseadapter.BaseHolder;
import com.xwtec.androidframe.base.baseadapter.RecycleViewSimpleAdapter;
import com.xwtec.androidframe.interfaces.RVItemClickListener;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomePresenterImpl> implements HomeContact.HomeView {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private String[] tabList = {"推荐","热销","精品","推荐","热销","精品"};
    private List<String> images = new ArrayList<>();
    private RecycleViewSimpleAdapter<GoodListBean> contentAdapter;

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void init() {
        initTitle();
        initContent();
        initBanner();
    }

    private void initTitle(){
        rvTitle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        final RecycleViewSimpleAdapter<String> titleAdapter = new RecycleViewSimpleAdapter<String>(context, R.layout.home_title_layout) {

            @Override
            protected void bindData(BaseHolder holder, String str) {
                holder.setText(R.id.tv_title, str);
            }
        };
        titleAdapter.setRadio(true);
        titleAdapter.setOnItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                titleAdapter.setSelectItem(postion);
                titleAdapter.notifyDataSetChanged();
                //请求对应类型的数据
            }
        });
        rvTitle.setAdapter(titleAdapter);
    }
    private void initContent(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        rvContent.setLayoutManager(gridLayoutManager);
        contentAdapter = new RecycleViewSimpleAdapter<GoodListBean>(context,R.layout.home_content_layout) {
            @Override
            protected void bindData(BaseHolder holder, GoodListBean goodListBean) {

            }
        };
        contentAdapter.setOnItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //跳转到详情页
            }
        });
        rvContent.setAdapter(contentAdapter);
    }
    private void initBanner(){
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528902547053&di=e0d004127d8c2eb1cfb124ad9a994c02&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0128605793108e0000012e7e5c9fc4.jpg%401280w_1l_2o_100sh.png");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528902528618&di=a904d15d8394cc65adda22d01d418030&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017bc158d0fb95a801219c77d5d770.png%401280w_1l_2o_100sh.png");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528902470507&di=26a7ce9bc45675c7e4a60a0683a718cb&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012daa59ad0aaca801211d25fb3a0e.png%401280w_1l_2o_100sh.png");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageLoadUtil.load(context,path,imageView);
            }
        });
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //banner点击事件
            }
        });
        banner.start();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
