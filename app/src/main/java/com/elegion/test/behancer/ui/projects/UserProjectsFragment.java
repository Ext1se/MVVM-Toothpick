package com.elegion.test.behancer.ui.projects;

import android.os.Bundle;

import com.elegion.test.behancer.common.BaseProjectsFragment;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.di.DI;
import com.elegion.test.behancer.di.models.UserProjectsFragmentModule;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class UserProjectsFragment extends BaseProjectsFragment {

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
    protected void injectDependencies() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, DI.USER_PROJECTS_FRAGMENT_SCOPE);
        scope.installModules(new UserProjectsFragmentModule(this));
        //scope.installModules(new TestModule());
        //mUsername = scope.getInstance(String.class, PROFILE_PROJECTS_KEY);
        Toothpick.inject(this, scope);
    }

    @Override
    protected void clearDependencies() {
        Toothpick.closeScope(DI.USER_PROJECTS_FRAGMENT_SCOPE);
    }
}
