package com.elegion.test.behancer.di.providers;

import android.arch.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsViewModel;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static com.elegion.test.behancer.common.BaseProjectsFragment.CLICK_ADAPTER;
import static com.elegion.test.behancer.common.BaseProjectsFragment.PROFILE_PROJECTS_KEY;

public class UserProjectsViewModelProvider implements Provider<UserProjectsViewModel> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    UserProjectsFragment mFragment;
    @Inject
    @Named(PROFILE_PROJECTS_KEY)
    String mUsername;
/*    @Inject
    @Named(CLICK_ADAPTER)
    ProjectsAdapter.OnItemClickListener mOnClickListener;*/

    @Override
    public UserProjectsViewModel get() {
        CustomFactoryProjects factory = new CustomFactoryProjects(mStorage, mApi, null, mUsername);
        return ViewModelProviders.of(mFragment, factory).get(UserProjectsViewModel.class);
    }
}
