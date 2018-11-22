package com.elegion.test.behancer.common;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.databinding.ProjectsBinding;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

public abstract class BaseProjectsFragment extends Fragment {
    public static final String PROFILE_KEY = "PROFILE_KEY";
    public static final String STATE_KEY = "STATE_KEY";

    protected BaseProjectsViewModel mViewModel;
    protected String mUsername = null;

    private ProjectsBinding mBinding;
    private Parcelable mState = null;

    protected ProjectsAdapter.OnItemClickListener mOnItemClickListener = (context, username) -> {
        mState = getState();
        Intent intent = new Intent(context, ProfileActivity.class);
        Bundle args = new Bundle();
        args.putString(ProfileFragment.PROFILE_KEY, username);
        intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        context.startActivity(intent);
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            if (getArguments() != null) {
                mUsername = getArguments().getString(PROFILE_KEY);
            }
            CustomFactoryProjects factory = new CustomFactoryProjects(storage, mOnItemClickListener, mUsername);
            mViewModel = getViewModel(factory);
        }
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

    protected abstract BaseProjectsViewModel getViewModel(CustomFactoryProjects factory);

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        mState = getState();
        outState.putParcelable(STATE_KEY, mState);
        super.onSaveInstanceState(outState);
    }

    private Parcelable getState() {
        return mBinding.recycler.getLayoutManager().onSaveInstanceState();
    }
}
