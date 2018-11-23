package com.elegion.test.behancer.di.providers;

import android.arch.lifecycle.ViewModelProviders;
import android.view.View;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.utils.CustomFactoryProfile;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.CLICK_PROFILE;
import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ProfileViewModelProvider implements Provider<ProfileViewModel> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    ProfileFragment mFragment;
    @Inject
    @Named(PROFILE_KEY)
    String mUsername;
    @Inject
    @Named(CLICK_PROFILE)
    View.OnClickListener mOnClickListener;

    @Override
    public ProfileViewModel get() {
        CustomFactoryProfile factory = new CustomFactoryProfile(mStorage, mApi, mUsername, mOnClickListener);
        return ViewModelProviders.of(mFragment, factory).get(ProfileViewModel.class);
    }
}
