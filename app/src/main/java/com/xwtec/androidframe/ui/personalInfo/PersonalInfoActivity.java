package com.xwtec.androidframe.ui.personalInfo;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.customView.WheelDatePicker;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.ImageLoadUtil;
import com.xwtec.androidframe.util.PopUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@Route(path = Constant.PERSONAL_ROUTER)
@RuntimePermissions
public class PersonalInfoActivity extends BaseActivity<PersonalPresenterImpl> implements PersonalContact.PersonalView, View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.iv_header)
    ImageView ivHeader;

    private UserBean userBean;
    private String userId = "app";
    private String filePath;
    private static final int CAMERA_REQUEST_CODE = 0;//调用系统相机请求码
    private static final int ALBUM_REQUEST_CODE = 1;//调用相册请求码
    private static final int CROP_REQUEST_CODE = 2;//裁剪图片的请求码

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(R.string.personalInfo);
        userBean = (UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY);
        if (userBean == null) {
            ARouter.getInstance().build(Constant.LOGIN_ROUTER).navigation();
            return;
        }
        String nickName = userBean.getNickName();
        String sex = userBean.getSex();
        String birthday = userBean.getBirth();
        String imgHead = userBean.getImgHead();
        if (!TextUtils.isEmpty(nickName)) {
            tvNickName.setText(nickName);
        }
        if (!TextUtils.isEmpty(sex)) {
            tvSex.setText("0".equals(sex) ? "女" : "男");
        }
        if (!TextUtils.isEmpty(birthday)) {
            tvBirthday.setText(birthday);
        }
        if (!TextUtils.isEmpty(imgHead)) {
            ImageLoadUtil.loadCenterCrop(this, imgHead, ivHeader);
        }

    }

    @OnClick({R.id.iv_left, R.id.ll_header, R.id.ll_nick_name, R.id.ll_sex, R.id.ll_birthday, R.id.ll_update_password, R.id.ll_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            //选择头像
            case R.id.ll_header:
                showHeaderPop();
                break;
            case R.id.ll_nick_name:
                showNickNameDialog();
                break;
            case R.id.ll_sex:
                showSexDialog();
                break;
            case R.id.ll_birthday:
                showBirthPop();
                break;
            case R.id.ll_update_password:
                ARouter.getInstance().build(Constant.UPDATE_PASSWORD_ROUTER).navigation();
                break;
            case R.id.ll_address:
                ARouter.getInstance().build(Constant.ADDRESS_ROUTER).navigation();
                break;
            case R.id.ll_female:
                updatePersonalInfo(null, "0", null);
                curSex = "女";
                dismissDialog(sexDialog);
                break;
            case R.id.ll_male:
                updatePersonalInfo(null, "1", null);
                curSex = "男";
                dismissDialog(sexDialog);
            case R.id.tv_cancel:
                dismissDialog(nickNameDialog);
                break;
            case R.id.tv_sure:
                String nickName = etDialogNickName.getText().toString().trim();
                if (TextUtils.isEmpty(nickName)) {
                    ToastUtils.showShort("昵称不能为空");
                    return;
                }
                curNickName = nickName;
                updatePersonalInfo(nickName, null, null);
                dismissDialog(nickNameDialog);
                break;
            case R.id.tv_cancel_birth:
                PopUtil.popDismiss(this, birthPop);
                break;
            case R.id.tv_finish_birth:
                Date currentDate = datePicker.getCurrentDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                curBirthday = simpleDateFormat.format(currentDate);
                updatePersonalInfo(null, null, curBirthday);
                PopUtil.popDismiss(this, birthPop);
                break;
            case R.id.tv_cancel_header:
                PopUtil.popDismiss(this, headerPop);
                break;
            case R.id.tv_photo_header:
                PersonalInfoActivityPermissionsDispatcher.cameraPermissionWithPermissionCheck(this);
                PopUtil.popDismiss(this, headerPop);
                break;
            case R.id.tv_album_header:
                PersonalInfoActivityPermissionsDispatcher.albunPermissionWithPermissionCheck(this);
                PopUtil.popDismiss(this, headerPop);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PersonalInfoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private Dialog sexDialog;
    private LinearLayout llFemale;
    private LinearLayout llMale;
    private String curNickName;
    private String curSex;
    private String curBirthday;

    private void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void showSexDialog() {
        if (sexDialog == null) {
            sexDialog = new Dialog(this, R.style.roundDialog);
            View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_sex, null);
            llFemale = contentView.findViewById(R.id.ll_female);
            llFemale.setOnClickListener(this);
            llMale = contentView.findViewById(R.id.ll_male);
            llMale.setOnClickListener(this);
            sexDialog.setContentView(contentView);
        }
        String curSex = tvSex.getText().toString().trim();
        if (curSex.equals("女")) {
            llFemale.setSelected(true);
            llMale.setSelected(false);
        } else {
            llFemale.setSelected(false);
            llMale.setSelected(true);
        }
        sexDialog.show();
    }

    private Dialog nickNameDialog;
    private EditText etDialogNickName;

    private void showNickNameDialog() {
        if (nickNameDialog == null) {
            nickNameDialog = new Dialog(this, R.style.roundDialog);
            View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_nick_name, null);
            etDialogNickName = contentView.findViewById(R.id.et_nick_name);
            contentView.findViewById(R.id.tv_cancel).setOnClickListener(this);
            contentView.findViewById(R.id.tv_sure).setOnClickListener(this);
            nickNameDialog.setContentView(contentView);
        }
        nickNameDialog.show();
    }

    private PopupWindow birthPop;
    private WheelDatePicker datePicker;

    private void showBirthPop() {
        if (birthPop == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.pop_birthday, null);
            birthPop = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            datePicker = contentView.findViewById(R.id.date_picker);
            datePicker.setAtmospheric(true);
            datePicker.setCyclic(true);
            datePicker.setItemSpace(ConvertUtils.dp2px(30));
            datePicker.setIndicatorColor(R.color.c_eaeaea);
            datePicker.setIndicatorSize(2);
            contentView.findViewById(R.id.tv_cancel_birth).setOnClickListener(this);
            contentView.findViewById(R.id.tv_finish_birth).setOnClickListener(this);
        }
        birthPop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private PopupWindow headerPop;

    private void showHeaderPop() {
        if (headerPop == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.pop_header, null);
            headerPop = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentView.findViewById(R.id.tv_cancel_header).setOnClickListener(this);
            contentView.findViewById(R.id.tv_photo_header).setOnClickListener(this);
            contentView.findViewById(R.id.tv_album_header).setOnClickListener(this);
        }
        PopUtil.popShowFromBottom(this, headerPop);
    }

    private void updatePersonalInfo(String nickName, String sex, String birthday) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nickName", nickName);
            jsonObject.put("sex", sex);
            jsonObject.put("birth", birthday);
            jsonObject.put("token", userBean.getToken());
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
            presenter.updatePersonalInfo(requestBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSuccess(String msg) {
        if (!TextUtils.isEmpty(curNickName)) {
            tvNickName.setText(curNickName);
            userBean.setNickName(curNickName);
        }
        if (!TextUtils.isEmpty(curSex)) {
            tvSex.setText(curSex);
            userBean.setSex(curSex);
        }
        if (!TextUtils.isEmpty(curBirthday)) {
            tvBirthday.setText(curBirthday);
            userBean.setBirth(curBirthday);
        }
        CacheUtils.getInstance().put(Constant.USER_KEY, userBean);
    }

    @Override
    public void uploadHeaderSuccess() {
        ToastUtils.showShort("上传成功");
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraPermission() {
        requestCamera();
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void albunPermission() {
        requestAlbum();
    }

    //调用系统相机
    private void requestCamera() {
        if (TextUtils.isEmpty(filePath)) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            }
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(filePath + "/camera/" + userId + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        file = new File(filePath + "/camera/" + userId + ".jpg");
        //兼容7.0的写法
        Uri desUri = FileProvider.getUriForFile(this, "com.anyan.headerdemo.fileprovider", file);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//授予目标应用临时权限
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);//保存在指定的位置
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    //调用系统相册
    private void requestAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ALBUM_REQUEST_CODE);
    }

    //调用裁剪功能
    private void crop(Uri fromUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(fromUri, "image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        File file = new File(filePath + "/crop/" + userId + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        file = new File(filePath + "/crop/" + userId + ".jpg");
        Uri desUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    private void updateHeader() {
        final File file = new File(filePath + "/crop/" + userId + ".jpg");
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", userBean.getToken());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        presenter.uploadHeader(map, body);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    //调用相机回调
                    Uri fromUri = FileProvider.getUriForFile(this, "com.anyan.headerdemo.fileprovider", new File(filePath + "/camera/" + userId + ".jpg"));
                    crop(fromUri);
                    break;
                case ALBUM_REQUEST_CODE:
                    //调用相册回调
                    Uri uri = data.getData();
                    crop(uri);
                    break;
                case CROP_REQUEST_CODE:
                    //调用裁剪回调
                    //请求服务器上传图片
                    updateHeader();
                    break;
            }
        }
    }

}
