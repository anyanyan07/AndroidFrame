package com.xwtec.androidframe.ui.address;

import com.xwtec.androidframe.base.BaseView;

import java.util.List;

/**
 * Created by ayy on 2018/6/23.
 * Describe:xxx
 */

public interface AddressContact {
    interface AddressView extends BaseView {
        void querySuccess(List<Address> addressList);
    }

    interface AddressPresenter {
        void queryAddress(String token);
    }
}
