package com.elegion.test.behancer.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.view.View;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;

public class CustomFactoryProfile extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    private String mUsername;
    private BehanceApi mApi;
    private View.OnClickListener mOnProfileClickListener;

    public CustomFactoryProfile(Storage storage, BehanceApi api, String username, View.OnClickListener onProfileClickListener) {
        mStorage = storage;
        mApi = api;
        mUsername = username;
        mOnProfileClickListener = onProfileClickListener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(mStorage, mApi, mUsername, mOnProfileClickListener);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
