package com.elegion.test.behancer.ui.profile;

import android.arch.lifecycle.LiveData;
import android.view.View;

import com.elegion.test.behancer.common.BaseViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.data.model.user.UserResponse;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel {

    private String mUsername;
    private View.OnClickListener mOnProfileClickListener;
    private LiveData<User> mUser;

    public ProfileViewModel(Storage storage, BehanceApi api, String username, View.OnClickListener onProfileClickListener) {
        super(storage, api);
        mStorage = storage;
        mApi = api;
        mOnProfileClickListener = onProfileClickListener;
        mUsername = username;
        mUser = mStorage.getUserLive(mUsername);
        updateData();
    }

    @Override
    public void updateData() {
        mDisposable = mApi.getUserInfo(mUsername)
                .map(UserResponse::getUser)
                .doOnSubscribe(disposable -> getIsLoading().postValue(true))
                .doFinally(() -> getIsLoading().postValue(false))
                .doOnSuccess(response -> getIsErrorVisible().postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> mStorage.insertUser(response),
                        throwable -> setIsErrorVisible());
    }

    @Override
    public void setIsErrorVisible() {
        boolean value = mUser.getValue() == null;
        getIsErrorVisible().postValue(value);
    }

    public View.OnClickListener getOnProfileClickListener() {
        return mOnProfileClickListener;
    }

    public LiveData<User> getUser() {
        setIsErrorVisible();
        return mUser;
    }
}
