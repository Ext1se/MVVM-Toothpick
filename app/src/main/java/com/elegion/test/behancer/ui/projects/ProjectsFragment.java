package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.common.BaseProjectsFragment;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.di.DI;
import com.elegion.test.behancer.di.models.ProjectsFragmentModule;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class ProjectsFragment extends BaseProjectsFragment {

    @Inject
    ProjectsViewModel mProjectsViewModel;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    protected BaseProjectsViewModel getViewModel() {
        return mProjectsViewModel;
    }

    @Override
    protected void injectDependencies() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, DI.PROJECTS_FRAGMENT_SCOPE);
        scope.installModules(new ProjectsFragmentModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    protected void clearDependencies() {
        Toothpick.closeScope(DI.PROJECTS_FRAGMENT_SCOPE);
    }
}
