package com.elegion.test.behancer.di.providers;

import android.arch.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsViewModel;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static com.elegion.test.behancer.di.models.UserProjectsFragmentModule.USER_PROJECTS_CLICK;
import static com.elegion.test.behancer.di.models.UserProjectsFragmentModule.USER_PROJECTS_USERNAME;

public class UserProjectsViewModelProvider implements Provider<UserProjectsViewModel> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    UserProjectsFragment mFragment;
    @Inject
    @Named(USER_PROJECTS_USERNAME)
    String mUsername;
    @Inject
    @Named(USER_PROJECTS_CLICK)
    ProjectsAdapter.OnItemClickListener mOnClickListener;

    @Override
    public UserProjectsViewModel get() {
        CustomFactoryProjects factory = new CustomFactoryProjects(mStorage, mApi, mOnClickListener, mUsername);
        return ViewModelProviders.of(mFragment, factory).get(UserProjectsViewModel.class);
    }
}
