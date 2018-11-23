package com.elegion.test.behancer.di.providers;

import android.arch.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static com.elegion.test.behancer.common.BaseProjectsFragment.CLICK_ADAPTER;

public class BaseProjectsViewModelProvider implements Provider<ProjectsViewModel> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    ProjectsFragment mFragment;
    @Inject
    @Named(CLICK_ADAPTER)
    ProjectsAdapter.OnItemClickListener mOnClickListener;

    @Override
    public ProjectsViewModel get() {
        CustomFactoryProjects factory = new CustomFactoryProjects(mStorage, mApi, mOnClickListener, null);
        return ViewModelProviders.of(mFragment, factory).get(ProjectsViewModel.class);
    }
}
