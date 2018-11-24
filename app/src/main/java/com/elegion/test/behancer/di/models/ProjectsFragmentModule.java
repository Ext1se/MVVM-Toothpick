package com.elegion.test.behancer.di.models;

import android.content.Intent;
import android.os.Bundle;

import com.elegion.test.behancer.di.providers.ProjectsViewModelProvider;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;

import toothpick.config.Module;

public class ProjectsFragmentModule extends Module {

    public static final String PROJECTS_CLICK = "PROJECTS_CLICK";

    private final ProjectsFragment mFragment;

    public ProjectsFragmentModule(ProjectsFragment fragment) {
        mFragment = fragment;
        bind(ProjectsFragment.class).toInstance(provideProjectsFragment());
        bind(ProjectsAdapter.OnItemClickListener.class).withName(PROJECTS_CLICK).toInstance(provideProjectsAdapterClick());
        bind(ProjectsViewModel.class).toProvider(ProjectsViewModelProvider.class).providesSingletonInScope();
    }

    ProjectsFragment provideProjectsFragment() {
        return mFragment;
    }

    ProjectsAdapter.OnItemClickListener provideProjectsAdapterClick() {
        return (context, username) ->
        {
            Intent intent = new Intent(context, ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, username);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            context.startActivity(intent);
        };
    }
}
