package com.elegion.test.behancer.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProjectsBinding;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

import javax.inject.Inject;

public abstract class BaseProjectsFragment extends BaseFragment {
    public static final String PROFILE_PROJECTS_KEY = "PROFILE_PROJECTS_KEY";
    public static final String STATE_KEY = "STATE_KEY";
    public static final String CLICK_ADAPTER = "CLICK_ADAPTER";

    protected BaseProjectsViewModel mViewModel;
    protected String mUsername;

    private ProjectsBinding mBinding;
    private Parcelable mState = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mViewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ProjectsBinding.inflate(inflater, container, false);
        mBinding.setVm(mViewModel);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getParcelable(STATE_KEY);
        }
        mBinding.setState(mState);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null && mUsername != null) {
            getActivity().setTitle(mUsername);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        mState = getState();
        outState.putParcelable(STATE_KEY, mState);
        super.onSaveInstanceState(outState);
    }

    private Parcelable getState() {
        return mBinding.recycler.getLayoutManager().onSaveInstanceState();
    }

    protected abstract BaseProjectsViewModel getViewModel();
}
