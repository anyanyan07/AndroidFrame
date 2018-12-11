package com.xwtec.androidframe.ui.orderDetail;

import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
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
import com.xwtec.androidframe.ui.orderDetail.bean.CanceledInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.FinishedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.ReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SendedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.SureReceivedInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitPayInfo;
import com.xwtec.androidframe.ui.orderDetail.bean.WaitSendInfo;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 待支付，已完成
 * 订单详情页
 */
@Route(path = Constant.ORDER_DETAIL_ROUTER)
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenterImpl> implements OrderDetailContact.OrderDetailView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_detail_address)
    TextView tvDetailAddress;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.good_price)
    PriceView goodPrice;
    @BindView(R.id.tv_good_unit_num)
    TextView tvGoodUnitNum;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.tv_total_money)
    PriceView tvTotalMoney;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_sure_receive)
    TextView tvSureReceive;
    @BindView(R.id.tv_money_return)
    TextView tvMoneyReturn;
    @BindView(R.id.tv_sale_return)
    TextView tvSaleReturn;

    private long orderId;
    private long goodId;
    private String token;
    private int status;
    private int position;

    private static final int MONEY_RETURN_REQ_CODE = 1;
    private static final int SALE_RETURN_REQ_CODE = 2;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.orderDetail);
        orderId = getIntent().getLongExtra("orderId", -1);
        goodId = getIntent().getLongExtra("goodId", -1);
        status = getIntent().getIntExtra("status", -1);
        position = getIntent().getIntExtra("position", -1);
        UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (orderId == -1 || userBean == null || status == -1 || position == -1) {
            ToastUtils.showShort("不好意思，出错了！");
            finish();
            return;
        }
        token = userBean.getToken();
        fetchDetailInfo(status);
    }

    private void fetchDetailInfo(int status) {
        switch (status) {
            case Constant.WAIT_PAY:
                tvStatus.setText("等待付款");
                tvCancel.setVisibility(View.VISIBLE);
                tvPay.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.VISIBLE);
                presenter.fetchWaitPayInfo(orderId, token);
                break;
            case Constant.FINISHED:
                tvStatus.setText("交易成功");
                tvDelete.setVisibility(View.VISIBLE);
                presenter.fetchFinishedInfo(orderId, token);
                break;
            case Constant.CANCELED:
                tvStatus.setText("交易关闭");
                tvDelete.setVisibility(View.VISIBLE);
                presenter.fetchCanceledInfo(orderId, token);
                break;
            case Constant.SENDED:
                tvStatus.setText("已发货");
                tvTime.setVisibility(View.VISIBLE);
                presenter.fetchSendedInfo(orderId, token);
                break;
            case Constant.RECEIVED://已收货，等待确认收货
                tvStatus.setText("已签收");
                tvTime.setVisibility(View.VISIBLE);
                tvSureReceive.setVisibility(View.VISIBLE);
                tvSaleReturn.setVisibility(View.VISIBLE);
                presenter.fetchReceivedInfo(orderId, token);
                break;
            case Constant.PAIED_WAIT_SEND:
                tvStatus.setText("等待发货");
                tvMoneyReturn.setVisibility(View.VISIBLE);
                presenter.fetchWaitSendInfo(orderId, token);
                break;
            case Constant.CANCELING:
                tvStatus.setText("取消中");
                presenter.fetchWaitPayInfo(orderId, token);
                break;
            case Constant.SURE_RECEIVED:
                tvStatus.setText("已确认收货");
                tvTime.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.VISIBLE);
                presenter.fetchSureReceivedInfo(orderId, token);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_pay, R.id.tv_delete, R.id.iv_left, R.id.tv_sure_receive,
            R.id.tv_money_return, R.id.tv_sale_return, R.id.ll_good_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            //取消订单
            case R.id.tv_cancel:
                presenter.cancelOrder(orderId, token);
                break;
            //立即付款
            case R.id.tv_pay:
                ARouter.getInstance().build(Constant.PAY_ROUTER)
                        .withString("totalMoney", tvTotalMoney.getPrice());
                break;
            //删除订单
            case R.id.tv_delete:
                presenter.deleteOrder(orderId + "", token);
                break;
            //确认收货
            case R.id.tv_sure_receive:
                presenter.sureReceive(orderId + "", token);
                break;
            //退款
            case R.id.tv_money_return:
                ARouter.getInstance().build(Constant.MONEY_RETURN_ROUTER)
                        .withInt("position", position)
                        .withLong("orderId", orderId).navigation(this, MONEY_RETURN_REQ_CODE);
                break;
            //退款
            case R.id.tv_sale_return:
                ARouter.getInstance().build(Constant.SALE_RETURN_ROUTER)
                        .withInt("position", position)
                        .withLong("orderId", orderId).navigation(this, SALE_RETURN_REQ_CODE);
                break;
            //跳转到商品详情
            case R.id.ll_good_info:
                ARouter.getInstance().build(Constant.GOODS_DETAIL_ROUTER)
                        .withLong("goodId", goodId)
                        .navigation();
                break;
            default:
                break;
        }
    }

    @Override
    public void fetchInfoSuccess(WaitPayInfo waitPayInfo) {
        tvTime.setText(waitPayInfo.getRemark());
        WaitPayInfo.ReceiveAddressBean address = waitPayInfo.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        ImageLoadUtil.loadFitCenter(this, waitPayInfo.getImgUrl(), ivGood);
        tvGoodName.setText(waitPayInfo.getTitle() + waitPayInfo.getIntroduction());
        goodPrice.setPrice(waitPayInfo.getUnitPrice());
        tvGoodUnitNum.setText("x" + waitPayInfo.getGoodsNumber());
        tvFreight.setText("￥" + waitPayInfo.getExpressPrice());
        tvTotalMoney.setPrice(waitPayInfo.getTotalPrice());
        tvOrderNum.setText(waitPayInfo.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", waitPayInfo.getCreateTime()));
    }

    @Override
    public void fetchFinishedSuccess(FinishedInfo info) {
        FinishedInfo.ReceiveAddressBean address = info.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        tvFreight.setText("￥" + info.getExpressPrice());
        tvTotalMoney.setPrice(info.getTotalPrice());
        tvOrderNum.setText(info.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", info.getCreateTime()));
    }

    @Override
    public void fetchCanceledSuccess(CanceledInfo info) {
        CanceledInfo.ReceiveAddressBean address = info.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        tvFreight.setText("￥" + info.getExpressPrice());
        tvTotalMoney.setPrice(info.getTotalPrice());
        tvOrderNum.setText(info.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", info.getCreateTime()));
    }

    @Override
    public void fetchSendedSuccess(SendedInfo info) {
        SendedInfo.ReceiveAddressBean address = info.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        tvFreight.setText("￥" + info.getExpressPrice());
        tvTotalMoney.setPrice(info.getTotalPrice());
        tvOrderNum.setText(info.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", info.getCreateTime()));
    }

    @Override
    public void fetchReceivedSuccess(ReceivedInfo info) {
        ReceivedInfo.ReceiveAddressBean address = info.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        tvTime.setText(info.getRemark());
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        tvFreight.setText("￥" + info.getExpressPrice());
        tvTotalMoney.setPrice(info.getTotalPrice());
        tvOrderNum.setText(info.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", info.getCreateTime()));
    }

    @Override
    public void fetchWaitSendSuccess(WaitSendInfo info) {
        WaitSendInfo.ReceiveAddressBean address = info.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        tvFreight.setText("￥" + info.getExpressPrice());
        tvTotalMoney.setPrice(info.getTotalPrice());
        tvOrderNum.setText(info.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", info.getCreateTime()));
    }

    @Override
    public void fetchSureReceivedSuccess(SureReceivedInfo info) {
        SureReceivedInfo.ReceiveAddressBean address = info.getReceiveAddress();
        if (address != null) {
            tvReceiver.setText(address.getReceiver());
            tvPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            tvDetailAddress.setText(address.getDetailsAddress());
        }
        tvTime.setText(info.getRemark());
        ImageLoadUtil.loadFitCenter(this, info.getImgUrl(), ivGood);
        tvGoodName.setText(info.getTitle() + info.getIntroduction());
        goodPrice.setPrice(info.getUnitPrice());
        tvGoodUnitNum.setText("x" + info.getGoodsNumber());
        tvFreight.setText("￥" + info.getExpressPrice());
        tvTotalMoney.setPrice(info.getTotalPrice());
        tvOrderNum.setText(info.getOrderNumber());
        tvCreateTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", info.getCreateTime()));
    }

    @Override
    public void cancelSuccess() {
        ToastUtils.showShort("订单取消成功");
        int[] data = {position, Constant.CANCELED};
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ORDER_CHANGE, data));
        finish();
    }

    @Override
    public void deleteSuccess() {
        ToastUtils.showShort("订单删除成功");
        int[] data = {position, Constant.DELETED};
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ORDER_CHANGE, data));
        finish();
    }

    @Override
    public void sureReceiveSuccess() {
        ToastUtils.showShort("已确认收货");
        int[] data = {position, Constant.SURE_RECEIVED};
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ORDER_CHANGE, data));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MONEY_RETURN_REQ_CODE:
                case SALE_RETURN_REQ_CODE:
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}
