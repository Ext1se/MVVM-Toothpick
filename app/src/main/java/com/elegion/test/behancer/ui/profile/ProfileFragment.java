package com.elegion.test.behancer.ui.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.common.BaseFragment;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.di.DI;
import com.elegion.test.behancer.di.models.ProfileFragmentModule;

import javax.inject.Inject;
import javax.inject.Named;

import toothpick.Scope;
import toothpick.Toothpick;

import static com.elegion.test.behancer.di.models.ProfileFragmentModule.PROFILE_USERNAME;

public class ProfileFragment extends BaseFragment {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    @Inject
    ProfileViewModel mProfileViewModel;
    @Inject
    @Named(PROFILE_USERNAME)
    String mUsername;

    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectDependencies() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, DI.PROFILE_FRAGMENT_SCOPE);
        scope.installModules(new ProfileFragmentModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    protected void clearDependencies() {
        Toothpick.closeScope(DI.PROFILE_FRAGMENT_SCOPE);
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
