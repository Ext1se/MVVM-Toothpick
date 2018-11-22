package com.elegion.test.behancer.ui.projects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.common.BaseProjectsFragment;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.common.BaseViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProjectsBinding;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

public class ProjectsFragment extends BaseProjectsFragment {

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    protected BaseProjectsViewModel getViewModel(CustomFactoryProjects factory) {
        return ViewModelProviders.of(this, factory).get(ProjectsViewModel.class);
    }
}
