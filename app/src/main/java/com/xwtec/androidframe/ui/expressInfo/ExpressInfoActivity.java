package com.xwtec.androidframe.ui.expressInfo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.EXPRESS_INFO_ROUTER)
public class ExpressInfoActivity extends BaseActivity<ExpressInfoPresenterImpl> implements ExpressInfoContact.ExpressInfoView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_express)
    RecyclerView rvExpress;

    private BaseQuickAdapter<ExpressInfo,BaseViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_express_info;
    }

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("物流信息");
        initRv();
        Intent intent = getIntent();
        if (intent != null) {
            String orderNum = intent.getStringExtra("orderNum");
            UserBean userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
            presenter.fetchExpressInfo(orderNum, userBean.getToken());
        }
    }

    private void initRv() {
        rvExpress.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter<ExpressInfo, BaseViewHolder>(R.layout.express_info_item) {
            @Override
            protected void convert(BaseViewHolder helper, ExpressInfo item) {
                String time = item.getTime();
                String[] timeArr = time.split(" ");
                if (timeArr.length==2){
                    helper.setText(R.id.tv_date,timeArr[0]);
                    helper.setText(R.id.tv_time,timeArr[1]);
                }else{
                    helper.setText(R.id.tv_date,time);
                }
                helper.setText(R.id.tv_express_address,item.getContext());
            }
        };
        rvExpress.setAdapter(adapter);
    }

    @Override
    public void fetchExpressInfoSuccess(List<ExpressInfo> expressInfoList) {
        adapter.setNewData(expressInfoList);
    }

    @OnClick(R.id.iv_left)
    public void onClick() {
        finish();
    }
}
