package com.xwtec.androidframe.ui.address;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.address.bean.Address;
import com.xwtec.androidframe.ui.address.bean.JsonBean;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constant.CREATE_ADDRESS_ROUTER)
public class CreateAddressActivity extends BaseActivity<CreateAddPresenterImpl> implements CreateAddContact.CreateAddView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_consignee)
    EditText etConsignee;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_detail_add)
    EditText etDetailAdd;
    @BindView(R.id.rl_set_default)
    RelativeLayout rlSetDefault;

    //类型：新增，修改
    private String type;
    private static final String ADD_TYPE = "ADD";
    private static final String EDIT_TYPE = "EDIT";
    private int id;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private OptionsPickerView cityPickerView;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private Thread thread;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    showCityPop();
                    break;
                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };


    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        Address address = (Address) intent.getSerializableExtra("address");
        if (address == null) {
            type = ADD_TYPE;
            tvTitle.setText("新增收货地址");
        } else {
            type = EDIT_TYPE;
            etConsignee.setText(address.getReceiver());
            etPhoneNum.setText(address.getPhone());
            tvAddress.setText(address.getReceiveArea());
            etDetailAdd.setText(address.getDetailsAddress());
            id = address.getId();
            rlSetDefault.setSelected(address.getIsDefault() == 1);
        }
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.finish);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_address;
    }


    @OnClick({R.id.iv_left, R.id.tv_right, R.id.rl_set_default, R.id.ll_chose_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            //完成编辑
            case R.id.tv_right:
                createAddress();
                break;
            case R.id.rl_set_default:
                boolean selected = rlSetDefault.isSelected();
                rlSetDefault.setSelected(!selected);
                break;
            case R.id.ll_chose_address:
                if (options1Items.size() <= 0) {
                    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                } else {
                    showCityPop();
                }
                break;
        }
    }

    private void createAddress() {
        String consignee = etConsignee.getText().toString().trim();
        if (TextUtils.isEmpty(consignee)) {
            ToastUtils.showShort("请填写收货人姓名");
            return;
        }
        String phoneNum = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }
        String address = tvAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showShort("请选择收货地址");
            return;
        }
        String detailAdd = etDetailAdd.getText().toString().trim();
        if (TextUtils.isEmpty(detailAdd)) {
            ToastUtils.showShort("请输入详细地址");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("receiver", consignee);
            jsonObject.put("receiveArea", address);
            jsonObject.put("detailsAddress", detailAdd);
            jsonObject.put("phone", phoneNum);
            jsonObject.put("isDefault", rlSetDefault.isSelected() ? 1 : 0);
            jsonObject.put("token", ((UserBean) CacheUtils.getInstance().getSerializable(Constant.USER_KEY)).getToken());
            if (ADD_TYPE.equals(type)) {
                presenter.createAdd(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
            } else {
                jsonObject.put("id", id);
                presenter.updateAddress(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSuccess(String msg) {
        ToastUtils.showShort(msg);
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ADDRESS_REFRESH, null));
        finish();
    }

    @Override
    public void updateSuccess(String msg) {
        ToastUtils.showShort(msg);
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ADDRESS_REFRESH, null));
        finish();
    }

    private void showCityPop() {
        if (cityPickerView == null) {
            cityPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String address = options1Items.get(options1).getPickerViewText() +
                            options2Items.get(options1).get(options2) +
                            options3Items.get(options1).get(options2).get(options3);
                    tvAddress.setText(address);
                }
            }).setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();
            cityPickerView.setPicker(options1Items, options2Items, options3Items);//三级选择器

        }
        cityPickerView.show();
    }

    private void initJsonData() {//解析数据
        String JsonData = getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
