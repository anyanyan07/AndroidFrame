package com.xwtec.androidframe.ui.comment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.FullyGridLayoutManager;
import com.xwtec.androidframe.util.PopUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Route(path = Constant.COMMENT_ROUTER)
public class CommentActivity extends BaseActivity<CommentPresenterImpl> implements CommentContact.CommentView, View.OnClickListener {

    private static final String TAG = "CommentActivity";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.mRatingBar)
    RatingBar mRatingBar;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private int maxSelectNum = 6;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private PopupWindow pop;
    private String orderNumber;
    private int position;


    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        if (intent != null) {
            orderNumber = intent.getStringExtra("orderNumber");
            position = intent.getIntExtra("position", -1);
        }
        tvTitle.setText("商品评价");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("发布");
        initWidget();
    }

    @Override
    public void commentSuccess() {
        ToastUtils.showShort("评价成功");
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_COMMENT_CHANGE, position));
        finish();
    }

    @Override
    public void commentFail(String msg) {
        ToastUtils.showShort(msg);
        dismissLoading();
        tvRight.setClickable(true);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_album:
                int selectNum = selectList == null ? maxSelectNum : maxSelectNum - selectList.size();
                PictureSelector.create(CommentActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(selectNum)
                        .minSelectNum(1)
                        .imageSpanCount(3)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                PopUtil.popDismiss(this, pop);
                break;
            case R.id.tv_camera:
                PictureSelector.create(CommentActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                PopUtil.popDismiss(this, pop);
                break;
            case R.id.tv_cancel:
                PopUtil.popDismiss(this, pop);
                break;
            default:
                break;
        }
    }


    @OnClick({R.id.iv_left, R.id.tv_right})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                showLoading();
                tvRight.setClickable(false);
                Map<String, RequestBody> map = new HashMap<>();
                map.put("token", RequestBody.create(MediaType.parse("text/plain"), ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken()));
                map.put("orderNumber", RequestBody.create(MediaType.parse("text/plain"), orderNumber));
                map.put("stars", RequestBody.create(MediaType.parse("text/plain"), ((int) mRatingBar.getRating())+""));
                map.put("comment", RequestBody.create(MediaType.parse("text/plain"), etComment.getText().toString().trim()));
                List<MultipartBody.Part> parts = new ArrayList<>(selectList.size());
                for (int i = 0; i < selectList.size(); i++) {
                    File file = new File(selectList.get(i).getPath());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part part = MultipartBody.Part.
                            createFormData("files", file.getName(), requestBody);
                    parts.add(part);
                }
                presenter.comment(map, parts);
                break;
            default:
                break;
        }
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(CommentActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(CommentActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(CommentActivity.this).externalPictureAudio(media.getPath());
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            showPop();
        }
    };

    private void showPop() {
        if (pop == null) {
            View bottomView = View.inflate(CommentActivity.this, R.layout.layout_bottom_dialog, null);
            TextView mAlbum = bottomView.findViewById(R.id.tv_album);
            TextView mCamera = bottomView.findViewById(R.id.tv_camera);
            TextView mCancel = bottomView.findViewById(R.id.tv_cancel);
            mAlbum.setOnClickListener(this);
            mCamera.setOnClickListener(this);
            mCancel.setOnClickListener(this);
            pop = new PopupWindow(bottomView, -1, -2);
            pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pop.setOutsideTouchable(true);
            pop.setFocusable(true);
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

}
