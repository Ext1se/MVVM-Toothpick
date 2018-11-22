package com.elegion.test.behancer.ui.projects;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.elegion.test.behancer.common.BaseProjectsFragment;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

public class UserProjectsFragment extends BaseProjectsFragment {

    public static UserProjectsFragment newInstance(Bundle args) {
        UserProjectsFragment fragment = new UserProjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseProjectsViewModel getViewModel(CustomFactoryProjects factory) {
        return ViewModelProviders.of(this, factory).get(UserProjectsViewModel.class);
    }
}
