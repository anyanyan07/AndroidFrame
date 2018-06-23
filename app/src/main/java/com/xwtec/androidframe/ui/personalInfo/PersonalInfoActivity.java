package com.xwtec.androidframe.ui.personalInfo;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.ImageLoadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constant.PERSONAL_ROUTER)
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
            tvSex.setText(sex);
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
                break;
            case R.id.ll_nick_name:
                showNickNameDialog();
                break;
            case R.id.ll_sex:
                showSexDialog();
                break;
            case R.id.ll_birthday:
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
            default:
                break;
        }
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
        }
        if (!TextUtils.isEmpty(curSex)) {
            tvSex.setText(curSex);
        }
        if (!TextUtils.isEmpty(curBirthday)) {
            tvBirthday.setText(curBirthday);
        }
    }
}
