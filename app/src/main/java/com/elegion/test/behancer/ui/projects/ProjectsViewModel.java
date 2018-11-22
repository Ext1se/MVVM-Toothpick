package com.elegion.test.behancer.ui.projects;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BaseProjectsViewModel;
import com.elegion.test.behancer.common.BaseViewModel;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel extends BaseProjectsViewModel {

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        super(storage, onItemClickListener);
        mProjects = mStorage.getProjectsPaged();
        updateData();
    }

    @Override
    protected Single<ProjectResponse> getApiProjects() {
        return ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY);
    }
}