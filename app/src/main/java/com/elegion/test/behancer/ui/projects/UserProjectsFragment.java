package com.elegion.test.behancer.ui.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.elegion.test.behancer.common.BaseProjectsFragment;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.di.DI;
import com.elegion.test.behancer.di.models.UserProjectsFragmentModule;

import javax.inject.Inject;
import javax.inject.Named;

import toothpick.Scope;
import toothpick.Toothpick;

import static com.elegion.test.behancer.di.models.UserProjectsFragmentModule.USER_PROJECTS_USERNAME;

public class UserProjectsFragment extends BaseProjectsFragment {

    @Inject
    @Named(USER_PROJECTS_USERNAME)
    String mUsername;
    @Inject
    UserProjectsViewModel mUserProjectsViewModel;

    public static UserProjectsFragment newInstance(Bundle args) {
        UserProjectsFragment fragment = new UserProjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseProjectsViewModel getViewModel() {
        return mUserProjectsViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null && mUsername != null) {
            getActivity().setTitle(mUsername);
        }
    }

    @Override
    protected void injectDependencies() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, DI.USER_PROJECTS_FRAGMENT_SCOPE);
        scope.installModules(new UserProjectsFragmentModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    protected void clearDependencies() {
        Toothpick.closeScope(DI.USER_PROJECTS_FRAGMENT_SCOPE);
    }
}
