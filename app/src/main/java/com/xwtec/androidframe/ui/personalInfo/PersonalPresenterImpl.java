package com.xwtec.androidframe.ui.personalInfo;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Created by ayy on 2018/6/21.
 * Describe:xxx
 */

public class PersonalPresenterImpl extends BasePresenter<PersonalContact.PersonalView> implements PersonalContact.PersonalPresenter {
    private NetResourceRepo netResourceRepo;

    @Inject
    public PersonalPresenterImpl(NetResourceRepo netResourceRepo) {
        this.netResourceRepo = netResourceRepo;
    }

    @Override
    public void updatePersonalInfo(RequestBody requestBody) {
        netResourceRepo.updatePersonalInfo(requestBody)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            view.updateSuccess(baseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
