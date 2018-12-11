package com.xwtec.androidframe.ui.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.SuperBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestActivity extends SuperBaseActivity {
    private static final String TAG = "TestActivity";
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    @Override
    protected void init() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("item" + i);
        }
        rvRight.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.answer_item, data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_answer, item);
            }
        };
        rvRight.setAdapter(adapter);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }


}
