package com.xwtec.androidframe.ui.express;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.EXPRESS_ROUTER)
public class ExpressActivity extends BaseActivity<ExpressPresenterImpl> implements ExpressContact.ExpressView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("物流公司");
        rv.setLayoutManager(new LinearLayoutManager(this));
        presenter.fetchExpressList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_express;
    }

    @Override
    public void fetchExpressSuccess(final List<Express> expressList) {
        BaseQuickAdapter<Express, BaseViewHolder> adapter = new BaseQuickAdapter<Express, BaseViewHolder>(R.layout.express_item,expressList) {
            @Override
            protected void convert(BaseViewHolder helper, Express item) {
                helper.setText(R.id.tv_express, item.getExpressCompany());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Express express = expressList.get(position);
                Intent data = new Intent();
                data.putExtra("expressId", express.getId());
                data.putExtra("expressName", express.getExpressCompany());
                setResult(RESULT_OK, data);
                finish();
            }
        });
        rv.setAdapter(adapter);
    }

    @OnClick(R.id.iv_left)
    public void onClick() {
        finish();
    }
}
