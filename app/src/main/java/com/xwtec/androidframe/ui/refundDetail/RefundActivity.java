package com.xwtec.androidframe.ui.refundDetail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.util.ImageLoadUtil;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.REFUND_DETAIL_ROUTER)
public class RefundActivity extends BaseActivity<RefundPresenterImpl> implements RefundContact.RefundView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_had_repeal)
    TextView tvHadRepeal;
    @BindView(R.id.ll_btns)
    LinearLayout llBtns;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.good_price)
    PriceView goodPrice;
    @BindView(R.id.tv_good_unit_num)
    TextView tvGoodUnitNum;
    @BindView(R.id.refund_price)
    PriceView refundPrice;
    @BindView(R.id.tv_refund_time)
    TextView tvRefundTime;

    private long orderId;
    private int status;
    private int position;
    private String token;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.refundDetail);
        Intent intent = getIntent();
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (intent != null) {
            orderId = intent.getLongExtra("orderId", -1);
            status = intent.getIntExtra("status", -1);
            position = intent.getIntExtra("position", -1);
        }
        if (orderId == -1 || status == -1 || position == -1 || userBean == null) {
            ToastUtils.showShort("出错了");
            finish();
            return;
        }
        token = userBean.getToken();
        fetchDetail(orderId, status);
    }

    private void fetchDetail(long orderId, int status) {
        switch (status) {
            //已退款
            case Constant.REFUNDED:
                break;
            //退款中
            case Constant.REFUNDING:
                tvStatus.setText("请等待处理");
                presenter.fetchRefundingInfo(orderId, token);
                break;
        }
    }

    @Override
    public void fetchRefundingSuccess(RefundingInfo info) {
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        RefundingInfo.RefundRecordBean refundRecord = info.getRefundRecord();
        if (refundRecord != null) {
            refundPrice.setPrice(refundRecord.getPrice());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refund;
    }

    @OnClick({R.id.iv_left, R.id.tv_repeal, R.id.tv_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_repeal:
                break;
            case R.id.tv_update:
                break;
            default:
                break;
        }
    }
}
