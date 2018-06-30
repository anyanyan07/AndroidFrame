package com.xwtec.androidframe.ui.address;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.base.BaseActivity;
import com.xwtec.androidframe.manager.Constant;
import com.xwtec.androidframe.ui.login.UserBean;
import com.xwtec.androidframe.util.PopUtil;
import com.xwtec.androidframe.util.RxBus.RxBus;
import com.xwtec.androidframe.util.RxBus.RxBusMSG;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    private List<Province> mProvinceData;
    private List<City> curCityList;
    private List<District> curDistrictList;
    private int cityIndex;
    private String procinceName;
    private String cityName;
    private String districtName;

    //类型：新增，修改
    private String type;
    private static final String ADD_TYPE = "ADD";
    private static final String EDIT_TYPE = "EDIT";
    private int id;


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
                try {
                    if (mProvinceData == null) {
                        parseJson();
                    }
                    showCityPop();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
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
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ADDRESS_REFRESH,null));
        finish();
    }

    @Override
    public void updateSuccess(String msg) {
        ToastUtils.showShort(msg);
        RxBus.getInstance().post(new RxBusMSG(Constant.RX_ADDRESS_REFRESH,null));
        finish();
    }

    private PopupWindow cityPop;
    private WheelPicker provincePicker;
    private WheelPicker cityPicker;
    private WheelPicker districtPicker;

    private void showCityPop() {
        if (cityPop == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.region_layout, null);
            cityPop = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView buttonOk = contentView.findViewById(R.id.pop_ok);
            TextView buttonCancel = contentView.findViewById(R.id.pop_cancel);
            provincePicker = contentView.findViewById(R.id.wheel_picker_province);
            cityPicker = contentView.findViewById(R.id.wheel_picker_city);
            districtPicker = contentView.findViewById(R.id.wheel_picker_district);
            provincePicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker picker, Object data, int position) {
                    Province province = mProvinceData.get(position);
                    procinceName = province.getProvinceName();
                    curCityList = province.getCities();
                    if (!curCityList.isEmpty()) {
                        cityPicker.setData(curCityList);
                        cityPicker.setSelectedItemPosition(0);
                    } else {
                        curCityList = new ArrayList<>();
                        cityPicker.setData(curCityList);
                    }
                }
            });
            cityPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker picker, Object data, int position) {
                    if (!curCityList.isEmpty()) {
                        City city = curCityList.get(position);
                        cityName = city.getCityName();
                        List<District> districtList = city.getDistrictList();
                        if (!districtList.isEmpty()) {
                            curDistrictList = districtList;
                            districtPicker.setData(districtList);
                            districtPicker.setSelectedItemPosition(0);
                        } else {
                            curDistrictList = new ArrayList<>();
                            districtPicker.setData(new ArrayList());
                        }
                    }
                }
            });
            districtPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker picker, Object data, int position) {
                    if (!curDistrictList.isEmpty()) {
                        districtName = curDistrictList.get(position).getDistrictName();
                    }
                }
            });
            cityPop.setBackgroundDrawable(new BitmapDrawable());
            cityPop.setOutsideTouchable(true);
            cityPop.setFocusable(true);
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvAddress.setText(procinceName + cityName + districtName);
                    PopUtil.popDismiss(CreateAddressActivity.this, cityPop);
                }
            });
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUtil.popDismiss(CreateAddressActivity.this, cityPop);
                }
            });
            provincePicker.setData(mProvinceData);
            cityPicker.setData(mProvinceData.get(0).getCities());
            districtPicker.setData(mProvinceData.get(0).getCities().get(0).getDistrictList());
        }
        PopUtil.popShowFromBottom(this, cityPop);
    }

    //解析xml
    private void parseJson() throws IOException, XmlPullParserException {
        InputStream in = getResources().getAssets().open("province_city_district.xml");
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(in, "utf-8");
        int event = parser.getEventType();
        Province province = null;
        City city = null;
        List<City> mCityData = null;
        List<District> districtList = null;
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    mProvinceData = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if ("p".equals(tagName)) {
                        province = new Province();
                        mCityData = new ArrayList<>();
                        int count = parser.getAttributeCount();
                        for (int i = 0; i < count; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("p_id".equals(attrName)) {
                                province.setProvinceId(attrValue);
                            }
                        }
                    }
                    if ("pn".equals(tagName)) {
                        province.setProvinceName(parser.nextText());
                    }
                    if ("c".equals(tagName)) {
                        city = new City();
                        districtList = new ArrayList<>();
                        int count1 = parser.getAttributeCount();
                        for (int i = 0; i < count1; i++) {
                            String attrName = parser.getAttributeName(i);
                            String attrValue = parser.getAttributeValue(i);
                            if ("c_id".equals(attrName)) {
                                city.setCityId(attrValue);
                            }
                        }
                    }
                    if ("cn".equals(tagName)) {
                        city.setCityName(parser.nextText());
                    }
                    if ("d".equals(tagName)) {
                        District district = new District();
                        district.setDistrictId(parser.getAttributeValue(0));
                        district.setDistrictName(parser.nextText());
                        districtList.add(district);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("c".equals(parser.getName())) {
                        city.setDistrictList(districtList);
                        mCityData.add(city);
                    }
                    if ("p".equals(parser.getName())) {
                        province.setCities(mCityData);
                        mProvinceData.add(province);
                    }
                    break;
            }
            event = parser.next();
        }
    }
}
