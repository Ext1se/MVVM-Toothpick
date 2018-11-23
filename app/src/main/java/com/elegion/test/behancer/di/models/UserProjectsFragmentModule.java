package com.elegion.test.behancer.di.models;

import com.elegion.test.behancer.di.providers.UserProjectsViewModelProvider;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsViewModel;

import toothpick.config.Module;

import static com.elegion.test.behancer.common.BaseProjectsFragment.CLICK_ADAPTER;
import static com.elegion.test.behancer.common.BaseProjectsFragment.PROFILE_PROJECTS_KEY;

public class UserProjectsFragmentModule extends Module {

    private final UserProjectsFragment mFragment;

    public UserProjectsFragmentModule(UserProjectsFragment fragment) {
        mFragment = fragment;
        bind(UserProjectsFragment.class).toInstance(mFragment);
        bind(String.class).withName(PROFILE_PROJECTS_KEY).toInstance(provideUsername());
        //bind(ProjectsAdapter.OnItemClickListener.class).withName(CLICK_ADAPTER).toInstance(provideProjectsAdapterClick());
        bind(UserProjectsViewModel.class).toProvider(UserProjectsViewModelProvider.class).providesSingletonInScope();
    }

    UserProjectsFragment provideUserProjectsFragment() {
        return mFragment;
    }

    String provideUsername() {
        return mFragment.getArguments().getString(PROFILE_PROJECTS_KEY);
    }

    ProjectsAdapter.OnItemClickListener provideProjectsAdapterClick() {
        return null;
    }
}
