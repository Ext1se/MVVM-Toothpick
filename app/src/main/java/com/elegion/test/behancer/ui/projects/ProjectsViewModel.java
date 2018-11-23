package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.data.model.project.ProjectResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectsViewModel extends BaseProjectsViewModel {

    @Inject
    public ProjectsViewModel(Storage storage, BehanceApi api, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        super(storage, api, onItemClickListener);
        mProjects = mStorage.getProjectsPaged();
        updateData();
    }

    @Override
    protected Single<ProjectResponse> getApiProjects() {
        return mApi.getProjects(BuildConfig.API_QUERY);
    }
}