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

import static com.elegion.test.behancer.ui.profile.ProfileFragment.CLICK_PROFILE;
import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ProfileFragmentModule extends Module {

    private final ProfileFragment mFragment;
    private String mUsername;

    public ProfileFragmentModule(ProfileFragment fragment) {
        mFragment = fragment;
        bind(ProfileFragment.class).toInstance(mFragment);
        bind(String.class).withName(PROFILE_KEY).toInstance(provideUsername());
        bind(View.OnClickListener.class).withName(CLICK_PROFILE).toInstance(provideProfileClickListener());
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
