package com.xwtec.androidframe.ui.personalInfo;

import com.xwtec.androidframe.base.BasePresenter;
import com.xwtec.androidframe.base.BaseResponse;
import com.xwtec.androidframe.base.ResponseObserver;
import com.xwtec.androidframe.manager.net.NetResourceRepo;
import com.xwtec.androidframe.ui.login.UserBean;

import javax.inject.Inject;

import okhttp3.MultipartBody;
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
                        super.onError(e);
                    }
                });
    }

    @Override
    public void uploadHeader(RequestBody token, MultipartBody.Part file) {
        netResourceRepo.uploadHeader(token, file)
                .subscribe(new ResponseObserver<BaseResponse>(this) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.uploadHeaderSuccess();
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void fetchUserInfo(String token) {
        netResourceRepo.fetchUserInfo(token)
                .subscribe(new ResponseObserver<BaseResponse<UserBean>>(this) {
                    @Override
                    public void onNext(BaseResponse<UserBean> baseResponse) {
                        if (view != null) {
                            if (baseResponse.isSuccess()) {
                                view.fetchUserInfoSuccess(baseResponse.getContent());
                            } else {
                                view.showLoadFail(baseResponse.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
