package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BaseView;
import com.xwtec.androidframe.ui.address.bean.Address;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public interface AddressContact {
    interface AddressView extends BaseView {
        void querySuccess(List<Address> addressList);

        void delSuccess(int position);

        void updateSuccess(String msg);
    }

    interface AddressPresenter {
        void queryAddress(String token);

        void delAdd(HashMap<String, Object> map, int position);

        void updateAddress(RequestBody body);
    }
}
