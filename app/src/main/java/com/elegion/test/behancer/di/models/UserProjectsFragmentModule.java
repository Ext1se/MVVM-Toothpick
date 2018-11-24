package com.elegion.test.behancer.di.models;

import android.content.Context;

import com.elegion.test.behancer.di.providers.UserProjectsViewModelProvider;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsViewModel;

import toothpick.config.Module;

import static com.elegion.test.behancer.common.BaseProjectsFragment.PROFILE_PROJECTS_KEY;

public class UserProjectsFragmentModule extends Module {

    public static final String USER_PROJECTS_USERNAME = "USER_PROJECTS_USERNAME";
    public static final String USER_PROJECTS_CLICK = "USER_PROJECTS_CLICK";

    private final UserProjectsFragment mFragment;

    public UserProjectsFragmentModule(UserProjectsFragment fragment) {
        mFragment = fragment;
        bind(UserProjectsFragment.class).toInstance(provideUserProjectsFragment());
        bind(String.class).withName(USER_PROJECTS_USERNAME).toInstance(provideUsername());
        bind(ProjectsAdapter.OnItemClickListener.class).withName(USER_PROJECTS_CLICK).toInstance(provideProjectsAdapterClick());
        bind(UserProjectsViewModel.class).toProvider(UserProjectsViewModelProvider.class).providesSingletonInScope();
    }

    UserProjectsFragment provideUserProjectsFragment() {
        return mFragment;
    }

    String provideUsername() {
        return mFragment.getArguments().getString(PROFILE_PROJECTS_KEY);
    }

    ProjectsAdapter.OnItemClickListener provideProjectsAdapterClick() {
        return (context, username) -> {
            //Если return null и заинжектить, то выпадет ошибка, так как инстансы не должны быть нулевыми
            //Поэтому пока просто оставляем логику пустой.
        };
    }
}
