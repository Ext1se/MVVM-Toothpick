package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.common.BaseViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends BaseProjectsViewModel {

    private String mUsername;

    @Inject
    public UserProjectsViewModel(Storage storage, BehanceApi api, ProjectsAdapter.OnItemClickListener onItemClickListener, String username) {
        super(storage, api, onItemClickListener);
        mUsername = username;
        mProjects = mStorage.getProjectsPagedByUsername(mUsername);
        updateData();
    }

    @Override
    protected Single<ProjectResponse> getApiProjects() {
        return mApi.getUserProjects(mUsername);
    }
}
