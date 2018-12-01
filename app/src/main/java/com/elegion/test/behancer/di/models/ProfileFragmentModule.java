package com.elegion.test.behancer.di.models;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.elegion.test.behancer.di.providers.ProfileViewModelProvider;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.ui.projects.UserProjectsActivity;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;

import toothpick.config.Module;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ProfileFragmentModule extends Module {

    public static final String PROFILE_USERNAME = "PROFILE_USERNAME";
    public static final String PROFILE_CLICK = "PROFILE_CLICK";

    private final ProfileFragment mFragment;
    private String mUsername;

    public ProfileFragmentModule(ProfileFragment fragment) {
        mFragment = fragment;
        bind(ProfileFragment.class).toInstance(provideProfileFragment());
        bind(String.class).withName(PROFILE_USERNAME).toInstance(provideUsername());
        bind(View.OnClickListener.class).withName(PROFILE_CLICK).toInstance(provideProfileClickListener());
        bind(ProfileViewModel.class).toProvider(ProfileViewModelProvider.class).providesSingletonInScope();
    }

    ProfileFragment provideProfileFragment() {
        return mFragment;
    }

    String provideUsername() {
        mUsername = mFragment.getArguments().getString(PROFILE_KEY);
        return mUsername;
    }

    View.OnClickListener provideProfileClickListener() {
        return view -> {
            //Не используем контекст фрагмента, так как он теряется при повороте
            Intent intent = new Intent(view.getContext(), UserProjectsActivity.class);
            Bundle args = new Bundle();
            args.putString(UserProjectsFragment.PROFILE_PROJECTS_KEY, mUsername);
            intent.putExtra(UserProjectsActivity.USERNAME_KEY, args);
            view.getContext().startActivity(intent);
        };
    }
}