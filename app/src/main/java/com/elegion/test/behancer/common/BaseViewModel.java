package com.elegion.test.behancer.common;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;

import io.reactivex.disposables.Disposable;

/**
 * todo: Проблема с ViewModel
 * Отключить интернет, войти в приложение, mIsErrorVisible = true, так как данные не успели подгрузиться из БД, показывается Error
 * При их загрузке mIsErrorVisible уже не меняется, а должен.
 * Частично исправлено с помощью вызовы метода setIsErrorVisible при получении данных
 */
public abstract class BaseViewModel extends ViewModel {

    protected Disposable mDisposable;
    protected Storage mStorage;
    protected BehanceApi mApi;

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateData;

    public BaseViewModel(Storage storage, BehanceApi api) {
        mStorage = storage;
        mApi = api;
    }

    @Override
    public void onCleared() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    abstract protected void updateData();

    abstract protected void setIsErrorVisible();

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
