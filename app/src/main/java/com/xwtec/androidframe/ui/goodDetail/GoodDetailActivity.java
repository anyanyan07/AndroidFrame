package com.xwtec.androidframe.ui.goodDetail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.interfaces.SimpleTextWatcher;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/activity/goodDetail")
public class GoodDetailActivity extends BaseActivity<GoodDetailPresenterImpl> implements GoodDetailContact.GoodDetailView, View.OnClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.btn_add_to_shop_cart)
    Button btnAdd;


    private GoodDetailAdapter goodDetailAdapter;
    private List<GoodDetailMultiEntity> data;
    private GoodDetailResponse goodDetailResponse;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("产品详情");
        ivLeft.setVisibility(View.VISIBLE);
        btnBuy.setEnabled(false);
        btnAdd.setEnabled(false);
        initRecyclerView();
        Intent intent = getIntent();
        if (intent != null) {
            long goodId = intent.getLongExtra("goodId", 1);
            presenter.fetchGoodDetail(goodId);
        }
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @OnClick({R.id.iv_left, R.id.iv_shop_cart, R.id.iv_share, R.id.btn_add_to_shop_cart, R.id.btn_buy})
    public void onClickButter(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_shop_cart:
                break;
            case R.id.iv_share:
                break;
            case R.id.btn_add_to_shop_cart:
                showPop();
                break;
            case R.id.btn_buy:
                break;

            default:
                break;
        }
    }

    private PopupWindow popupWindow;

    private ImageView ivGood;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvFlavor;
    private TextView tvNum;
    private TextView tvAdd;
    private TextView tvReduce;
    private Button btnSure;

    private void showPop() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.good_detail_pop, null);
            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            ivGood = contentView.findViewById(R.id.iv_good);
            tvName = contentView.findViewById(R.id.tv_name);
            tvPrice = contentView.findViewById(R.id.tv_price);
            tvFlavor = contentView.findViewById(R.id.tv_flavor);
            tvNum = contentView.findViewById(R.id.tv_num);
            tvAdd = contentView.findViewById(R.id.tv_add);
            tvReduce = contentView.findViewById(R.id.tv_reduce);
            btnSure = contentView.findViewById(R.id.btn_sure);
            btnSure.setOnClickListener(this);
            tvAdd.setOnClickListener(this);
            tvReduce.setOnClickListener(this);
            tvNum.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    int curNum = Integer.parseInt(s.toString().trim());
                    if (curNum <= 1) {
                        tvReduce.setClickable(false);
                    } else {
                        tvReduce.setClickable(true);
                    }
                }
            });
        }
        if (goodDetailResponse == null) {
            return;
        }
        List<GoodDetailResponse.DetailImgListBean> detailImgList = goodDetailResponse.getDetailImgList();
        if (detailImgList != null && detailImgList.size() > 0) {
            ImageLoadUtil.loadCenterCrop(this, detailImgList.get(0).getImgUrl(), ivGood);
        }
        tvName.setText(goodDetailResponse.getTitle() + goodDetailResponse.getIntroduction());
        tvPrice.setText(goodDetailResponse.getDiscountPrice());
        tvFlavor.setText("原味");
        getWindow().getAttributes().alpha = 0.5f;
        getWindow().setAttributes(getWindow().getAttributes());
        popupWindow.showAtLocation(llRoot, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void fetchGoodDetailSuccess(GoodDetailResponse goodDetailResponse) {
        this.goodDetailResponse = goodDetailResponse;
        btnBuy.setEnabled(true);
        btnAdd.setEnabled(true);
        if (data == null) {
            data = new ArrayList<>();
        }
        GoodDetailMultiEntity<GoodDetailResponse.DetailImgListBean> banner = new GoodDetailMultiEntity<>(GoodDetailAdapter.BANNER_TYPE);
        banner.setDataList(goodDetailResponse.getDetailImgList());
        GoodDetailMultiEntity<GoodDetailResponse> description = new GoodDetailMultiEntity<>(GoodDetailAdapter.DESCRIPTION_TYPE);
        description.setData(goodDetailResponse);
        GoodDetailMultiEntity txt = new GoodDetailMultiEntity(GoodDetailAdapter.TXT_TYPE);
        GoodDetailMultiEntity<GoodDetailResponse.DetailImgTextListBean> imgs = new GoodDetailMultiEntity<>(GoodDetailAdapter.IMGS_TYPE);
        imgs.setDataList(goodDetailResponse.getDetailImgTextList());
        data.add(banner);
        data.add(description);
        data.add(txt);
        data.add(imgs);
        goodDetailAdapter = new GoodDetailAdapter(data);
        rv.setAdapter(goodDetailAdapter);
    }

    private void addSure() {
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean == null) {
            ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsId", goodDetailResponse.getId());
        map.put("goodsNumber", tvNum.getText().toString().trim());
        map.put("token", userBean.getToken());
        presenter.addShopCart(map);
    }

    @Override
    public void addShopCartSuccess() {
        ToastUtils.showShort("添加购物车成功");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                addSure();
                dismissPop();
                break;
            case R.id.tv_add:
                int num = Integer.parseInt(tvNum.getText().toString().trim()) + 1;
                tvNum.setText(num + "");
                break;
            case R.id.tv_reduce:
                int curNum = Integer.parseInt(tvNum.getText().toString().trim()) - 1;
                tvNum.setText(curNum + "");
                break;
            default:
                break;
        }
    }

    private void dismissPop() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            getWindow().getAttributes().alpha = 1f;
            getWindow().setAttributes(getWindow().getAttributes());
        }
    }
}
