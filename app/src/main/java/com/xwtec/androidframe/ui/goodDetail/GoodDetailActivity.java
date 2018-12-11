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
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.interfaces.SimpleTextWatcher;
import com.xwtec.androidframe.manager.App;
import com.xwtec.androidframe.manager.AppManager;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailMultiEntity;
import com.xwtec.androidframe.ui.goodDetail.bean.GoodDetailResponse;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.main.MainActivity;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.PopUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private GoodDetailMultiEntity<CommentInfo> comment;
    private GoodDetailMultiEntity<GoodDetailResponse.DetailImgListBean> banner;
    private GoodDetailMultiEntity<GoodDetailResponse> description;
    private GoodDetailMultiEntity<GoodDetailResponse.DetailImgTextListBean> imgs;
    private GoodDetailResponse goodDetailResponse;
    private PopupWindow popupWindow;
    private long goodId;

    private ImageView ivGood;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvFlavor;
    private TextView tvNum;
    private TextView tvAdd;
    private TextView tvReduce;
    private Button btnSure;

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        if (intent != null) {
            goodId = intent.getLongExtra("goodId", -1);
        }
        if (goodId == -1) {
            ToastUtils.showShort("sorry,出错了");
            finish();
            return;
        }
        tvTitle.setText("产品详情");
        ivLeft.setVisibility(View.VISIBLE);
        btnBuy.setEnabled(false);
        btnAdd.setEnabled(false);
        initRecyclerView();
        presenter.fetchGoodDetail(goodId);
        presenter.fetchComment(goodId, -1, Constant.FIRST_PAGE_INDEX, 3);
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if (data == null) {
            data = new ArrayList<>();
        }
        banner = new GoodDetailMultiEntity<>(GoodDetailAdapter.BANNER_TYPE);
        description = new GoodDetailMultiEntity<>(GoodDetailAdapter.DESCRIPTION_TYPE);
        GoodDetailMultiEntity txt = new GoodDetailMultiEntity(GoodDetailAdapter.TXT_TYPE);
        GoodDetailMultiEntity allComment = new GoodDetailMultiEntity(GoodDetailAdapter.ALL_COMMENT_TYPE);
        imgs = new GoodDetailMultiEntity<>(GoodDetailAdapter.IMGS_TYPE);
        comment = new GoodDetailMultiEntity<>(GoodDetailAdapter.COMMENT_TYPE);
        data.add(banner);
        data.add(description);
        data.add(txt);
        data.add(imgs);
        data.add(allComment);
        data.add(comment);
        goodDetailAdapter = new GoodDetailAdapter(data, goodId);
        rv.setAdapter(goodDetailAdapter);
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
                RxBus.getInstance().post(new RxBusMSG(Constant.RX_GO_SHOP_CART, null));
                AppManager.getInstance().backToActivity(MainActivity.class);
                break;
            case R.id.iv_share:
                showSharePop();
                break;
            case R.id.btn_add_to_shop_cart:
                showPop();
                break;
            //立即购买跳到确认订单页面
            case R.id.btn_buy:
                UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
                if (userBean == null) {
                    ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
                    return;
                }
                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject params = new JSONObject();
                    params.put("goodsId", goodDetailResponse.getId());
                    params.put("goodsNumber", 1);
                    jsonArray.put(params);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("token", userBean.getToken());
                    jsonObject.put("parameter", jsonArray);
                    ARouter.getInstance().build(Constant.AFFIRM_ORDER_ROUTER)
                            .withString("json", jsonObject.toString()).navigation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

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
            contentView.findViewById(R.id.iv_dismiss).setOnClickListener(this);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    getWindow().getAttributes().alpha = 1.0f;
                    getWindow().setAttributes(getWindow().getAttributes());
                }
            });
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
        tvNum.setText("1");
        tvName.setText(goodDetailResponse.getTitle() + goodDetailResponse.getIntroduction());
        tvPrice.setText(goodDetailResponse.getDiscountPrice());
//        tvFlavor.setText("原味");
        getWindow().getAttributes().alpha = 0.5f;
        getWindow().setAttributes(getWindow().getAttributes());
        popupWindow.showAtLocation(llRoot, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void fetchGoodDetailSuccess(GoodDetailResponse goodDetailResponse) {
        this.goodDetailResponse = goodDetailResponse;
        btnBuy.setEnabled(true);
        btnAdd.setEnabled(true);
        banner.setDataList(goodDetailResponse.getDetailImgList());
        description.setData(goodDetailResponse);
        imgs.setDataList(goodDetailResponse.getDetailImgTextList());
        goodDetailAdapter.updateDetail();
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
    public void fetchCommentSuccess(List<CommentInfo> commentInfoList) {
        comment.setDataList(commentInfoList);
        goodDetailAdapter.updateComment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                addSure();
                PopUtil.popDismiss(this, popupWindow);
                break;
            case R.id.tv_add:
                int num = Integer.parseInt(tvNum.getText().toString().trim()) + 1;
                tvNum.setText(num + "");
                break;
            case R.id.tv_reduce:
                int curNum = Integer.parseInt(tvNum.getText().toString().trim()) - 1;
                tvNum.setText(curNum + "");
                break;
            case R.id.iv_dismiss:
                popupWindow.dismiss();
                break;
            case R.id.ll_share_wx:
                shareWx(SendMessageToWX.Req.WXSceneSession);
                PopUtil.popDismiss(this, sharePop);
                break;
            case R.id.ll_share_friend_circle:
                shareWx(SendMessageToWX.Req.WXSceneTimeline);
                PopUtil.popDismiss(this, sharePop);
                break;
            default:
                break;
        }
    }

    private PopupWindow sharePop;

    /**
     * 分享弹窗
     */
    private void showSharePop() {
        if (sharePop == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.share_pop, null);
            sharePop = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            sharePop.setOutsideTouchable(true);
            LinearLayout llShareWx = contentView.findViewById(R.id.ll_share_wx);
            LinearLayout llShareFriendCircle = contentView.findViewById(R.id.ll_share_friend_circle);
            llShareWx.setOnClickListener(this);
            llShareFriendCircle.setOnClickListener(this);
            sharePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    getWindow().getAttributes().alpha = 1.0f;
                    getWindow().setAttributes(getWindow().getAttributes());
                }
            });
        }
        PopUtil.popShowFromBottom(this, sharePop);

    }

    private void shareWx(int scene) {
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.xwtec.androidframe";
        WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
        wxMediaMessage.title = "东北农场";
        wxMediaMessage.description = "生活就应该吃点不一样的，“东北农场”一个食于特色、食出健康的品牌！";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "webPage" + String.valueOf(System.currentTimeMillis());
        req.message = wxMediaMessage;
        req.scene = scene;
        ((App) getApplication()).getWxApi().sendReq(req);
    }

    @Override
    public void onBackPressed() {
        if (sharePop != null && sharePop.isShowing()) {
            PopUtil.popDismiss(this, sharePop);
            return;
        }
        if (popupWindow != null && popupWindow.isShowing()) {
            PopUtil.popDismiss(this, popupWindow);
            return;
        }
        super.onBackPressed();
    }
}
