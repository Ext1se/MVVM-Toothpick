package com.elegion.test.behancer.ui.profile;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.ui.projects.UserProjectsActivity;
import com.elegion.test.behancer.utils.CustomFactoryProfile;
import com.elegion.test.behancer.utils.CustomFactoryProjects;

public class ProfileFragment extends Fragment {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    private ProfileViewModel mProfileViewModel;
    private String mUsername;
    private View.OnClickListener mOnProfileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Не используем контекст фрагмента, так как он теряется при повороте
            Intent intent = new Intent(view.getContext(), UserProjectsActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, mUsername);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            view.getContext().startActivity(intent);
        }
    };

    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner && getArguments() != null) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mUsername = getArguments().getString(PROFILE_KEY);
            CustomFactoryProfile factory = new CustomFactoryProfile(storage, mUsername, mOnProfileClickListener);
            mProfileViewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileBinding binding = ProfileBinding.inflate(inflater, container, false);
        binding.setVm(mProfileViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
        }
    }
}
