package com.elegion.test.behancer.di.models;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.elegion.test.behancer.di.providers.ProfileViewModelProvider;
import com.elegion.test.behancer.di.providers.ProjectsViewModelProvider;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileViewModel;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.ui.projects.UserProjectsActivity;

import toothpick.config.Module;

import static com.elegion.test.behancer.common.BaseProjectsFragment.CLICK_ADAPTER;
import static com.elegion.test.behancer.ui.profile.ProfileFragment.CLICK_PROFILE;
import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ProjectsFragmentModule extends Module {

    private final ProjectsFragment mFragment;

    public ProjectsFragmentModule(ProjectsFragment fragment) {
        mFragment = fragment;
        bind(ProjectsFragment.class).toInstance(mFragment);
        bind(ProjectsAdapter.OnItemClickListener.class).withName(CLICK_ADAPTER).toInstance(provideProjectsAdapterClick());
        bind(ProjectsViewModel.class).toProvider(ProjectsViewModelProvider.class).providesSingletonInScope();
    }

    ProjectsFragment provideProjectsFragment() {
        return mFragment;
    }

    ProjectsAdapter.OnItemClickListener provideProjectsAdapterClick() {
        return (context, username) ->
        {
            //mState = getState();
            Intent intent = new Intent(context, ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, username);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            context.startActivity(intent);
        };
    }
}
