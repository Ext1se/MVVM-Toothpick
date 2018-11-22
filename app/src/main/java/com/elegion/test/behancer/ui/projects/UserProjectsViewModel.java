package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.common.BaseViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends BaseProjectsViewModel {

    private String mUsername;

    public UserProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener, String username) {
        super(storage, onItemClickListener);
        mUsername = username;
        mProjects = mStorage.getProjectsPagedByUsername(mUsername);
        updateData();
    }

    @Override
    protected Single<ProjectResponse> getApiProjects() {
        return ApiUtils.getApiService().getUserProjects(mUsername);
    }
}
