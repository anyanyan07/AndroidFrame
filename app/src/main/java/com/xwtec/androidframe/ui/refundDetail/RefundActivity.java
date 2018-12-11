package com.xwtec.androidframe.ui.refundDetail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.PriceView;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.RefundingInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturnedInfo;
import com.xwtec.androidframe.ui.refundDetail.bean.SalesReturningInfo;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

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
    @BindView(R.id.ll_btn_bottom)
    LinearLayout llBtnBottom;
    @BindView(R.id.tv_case_name)
    TextView tvCaseName;
    @BindView(R.id.tv_case_content)
    TextView tvCaseContent;
    @BindView(R.id.ll_return_money)
    LinearLayout llReturnMoney;
    @BindView(R.id.ll_return_time)
    LinearLayout llReturnTime;
    @BindView(R.id.tv_return_type)
    TextView tvReturnType;

    private long orderId;
    private long goodId;
    private int status;
    private int position;
    private String token;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.orderDetail);
        Intent intent = getIntent();
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (intent != null) {
            orderId = intent.getLongExtra("orderId", -1);
            goodId = intent.getLongExtra("goodId", -1);
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
                llReturnMoney.setVisibility(View.VISIBLE);
                llReturnTime.setVisibility(View.VISIBLE);
                tvStatus.setText("退款成功");
                tvCaseName.setText("退款原因");
                tvReturnType.setText("退款信息");
                llBtnBottom.setVisibility(View.VISIBLE);
                presenter.fetchRefundedInfo(orderId, token);
                break;
            //退款中
            case Constant.REFUNDING:
                llBtns.setVisibility(View.VISIBLE);
                tvCaseName.setText("退款原因");
                tvStatus.setText("请等待处理");
                tvReturnType.setText("退款信息");
                presenter.fetchRefundingInfo(orderId, token);
                break;
            case Constant.SALE_RETURNED:
                tvStatus.setText("退货成功");
                tvCaseName.setText("退货原因");
                tvReturnType.setText("退货信息");
                llBtnBottom.setVisibility(View.VISIBLE);
                presenter.fetchSaleReturnedInfo(orderId, token);
                break;
            case Constant.SALE_RETURNING:
                tvStatus.setText("退货中");
                tvCaseName.setText("退货原因");
                tvReturnType.setText("退货信息");
                presenter.fetchSaleReturningInfo(orderId, token);
                break;
            default:
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
            tvCaseContent.setText(refundRecord.getCause());
        }
    }

    @Override
    public void fetchRefundedSuccess(RefundedInfo info) {
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        RefundedInfo.RefundRecordBean refundRecord = info.getRefundRecord();
        if (refundRecord != null) {
            refundPrice.setPrice(refundRecord.getPrice());
            tvCaseContent.setText(refundRecord.getCause());
        }
    }

    @Override
    public void fetchSaleReturnedSuccess(SalesReturnedInfo info) {
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        SalesReturnedInfo.ReturnGoodsRecordBean returnRecord = info.getReturnGoodsRecord();
        if (returnRecord != null) {
            tvCaseContent.setText(returnRecord.getCause());
        }
    }

    @Override
    public void fetchSaleReturningSuccess(SalesReturningInfo info) {
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        SalesReturningInfo.ReturnGoodsRecordBean returnRecord = info.getReturnGoodsRecord();
        if (returnRecord != null) {
            tvCaseContent.setText(returnRecord.getCause());
        }
    }

    @Override
    public void deleteSuccess() {
        ToastUtils.showShort("订单删除成功");
        int[] data = {position, Constant.DELETED};
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ORDER_CHANGE, data));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refund;
    }

    @OnClick({R.id.iv_left, R.id.tv_repeal, R.id.tv_update, R.id.tv_delete,R.id.ll_good_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_repeal:
                break;
            case R.id.tv_update:
                break;
            case R.id.tv_delete:
                presenter.deleteOrder(orderId + "", token);
                break;
            case R.id.ll_good_info:
                ARouter.getInstance().build(Constant.GOODS_DETAIL_ROUTER)
                        .withLong("goodId", goodId)
                        .navigation();
                break;
            default:
                break;
        }
    }
}
