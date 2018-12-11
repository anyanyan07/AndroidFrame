package com.xwtec.androidframe.ui.comments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.goodDetail.CommentImgsAdapter;
import com.xwtec.androidframe.ui.goodDetail.bean.CommentInfo;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.PopUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.ALL_COMMENT_ROUTER)
public class CommentsActivity extends BaseActivity<CommentsPresenterImpl> implements CommentsContact.CommentsView, OnRefreshLoadMoreListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.ll_tabs)
    LinearLayout llTabs;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_good_comment)
    TextView tvGoodComment;
    @BindView(R.id.tv_medium_comment)
    TextView tvMediumComment;
    @BindView(R.id.tv_bad_comment)
    TextView tvBadComment;
    @BindView(R.id.tv_have_img)
    TextView tvHaveImg;

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private BaseQuickAdapter<CommentInfo, BaseViewHolder> adapter;
    private List<CommentInfo> commentData;
    private long goodId;
    private int commentLevel = -1;
    private int pageIndex = Constant.FIRST_PAGE_INDEX;
    private PopupWindow picturePop;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comments;
    }

    @Override
    protected void init() {
        super.init();
        tvTitle.setText("商品评价");
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        initRv();
        Intent intent = getIntent();
        if (intent != null) {
            goodId = intent.getLongExtra("goodId", -1);
        }
        setActiveTab(tvAll);
        fetchComment(0);
    }

    private void fetchComment(int pageIndex) {
        this.pageIndex = pageIndex;
        presenter.fetchComments(goodId, commentLevel, pageIndex, Constant.PER_PAGE_NUM);
    }

    private void initRv() {
        commentData = new ArrayList<>();
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter<CommentInfo, BaseViewHolder>(R.layout.comment_item, commentData) {
            @Override
            protected void convert(BaseViewHolder helper, CommentInfo item) {
                ImageView ivHeader = helper.getView(R.id.iv_header);
                ImageLoadUtil.loadCircleImage(mContext, item.getHeadImg(), ivHeader);
                helper.setText(R.id.tv_name, item.getNickName());
                helper.setText(R.id.tv_date, DateFormat.format("yyyy-MM-dd", item.getCommentDate()));
                helper.setText(R.id.tv_comment, item.getComment());
                final List<String> imgUrlList = item.getImgUrlList();
                GridView gvImgs = helper.getView(R.id.gv_imgs);
                gvImgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showPicture(imgUrlList, position);
                    }
                });
                gvImgs.setAdapter(new CommentListImgsAdapter(mContext, imgUrlList,goodId));
            }
        };
        adapter.setEmptyView(R.layout.empty_layout, llComment);
        rvComment.setAdapter(adapter);
    }

    @Override
    public void fetchCommentsSuccess(List<CommentInfo> commentInfoList) {
        if (commentInfoList != null) {
            if (pageIndex == 0) {
                smartRefreshLayout.finishRefresh(true);
                commentData.clear();
            } else {
                smartRefreshLayout.finishLoadMore(true);
            }
            if (commentInfoList.size() < Constant.PER_PAGE_NUM) {
                //不足一页
                smartRefreshLayout.setNoMoreData(true);
            }
            pageIndex += commentInfoList.size();
            commentData.addAll(commentInfoList);
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.iv_left, R.id.tv_all, R.id.tv_good_comment, R.id.tv_medium_comment, R.id.tv_bad_comment, R.id.tv_have_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_all:
                setActiveTab(tvAll);
                commentLevel = -1;
                fetchComment(0);
                break;
            case R.id.tv_good_comment:
                setActiveTab(tvGoodComment);
                commentLevel = 1;
                fetchComment(0);
                break;
            case R.id.tv_medium_comment:
                setActiveTab(tvMediumComment);
                commentLevel = 2;
                fetchComment(0);
                break;
            case R.id.tv_bad_comment:
                setActiveTab(tvBadComment);
                commentLevel = 3;
                fetchComment(0);
                break;
            case R.id.tv_have_img:
                setActiveTab(tvHaveImg);
                commentLevel = 4;
                fetchComment(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        fetchComment(pageIndex);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        fetchComment(0);
    }

    private void setActiveTab(TextView activeTab) {
        llTabs.getChildCount();
        for (int i = 0; i < llTabs.getChildCount(); i++) {
            View view = llTabs.getChildAt(i);
            view.setSelected(false);
        }
        activeTab.setSelected(true);
    }

    private ViewPager vpPicture;
    private PictureAdapter pictureAdapter;

    /**
     * 显示大图
     */
    private void showPicture(List<String> imgUrls, int position) {
        if (picturePop == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.picture_pop, null);
            picturePop = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            vpPicture = contentView.findViewById(R.id.vp_picture);
            pictureAdapter = new PictureAdapter(this);
            vpPicture.setAdapter(pictureAdapter);
        }
        pictureAdapter.setNewData(imgUrls);
        vpPicture.setCurrentItem(position);
        PopUtil.popShowAtCenter(this, picturePop);
    }

    @Override
    public void onBackPressed() {
        if (picturePop != null && picturePop.isShowing()) {
            PopUtil.popDismiss(this, picturePop);
        } else {
            super.onBackPressed();
        }
    }
}
