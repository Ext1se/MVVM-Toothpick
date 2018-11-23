package com.elegion.test.behancer.common;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.data.model.project.ProjectResponse;
import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseProjectsViewModel extends BaseViewModel {

    protected LiveData<PagedList<RichProject>> mProjects;
    protected ProjectsAdapter.OnItemClickListener mOnItemClickListener;

    public BaseProjectsViewModel(Storage storage, BehanceApi api, ProjectsAdapter.OnItemClickListener onItemClickListener)
    {
        super();
        mStorage = storage;
        mApi = api;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void updateData()
    {
        mDisposable = getApiProjects()
                .map(ProjectResponse::getProjects)
                .doOnSubscribe(disposable -> getIsLoading().postValue(true))
                .doFinally(() -> getIsLoading().postValue(false))
                .doOnSuccess(response -> getIsErrorVisible().postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> mStorage.insertProjects(response),
                        throwable -> setIsErrorVisible()
                );
    }

    abstract protected Single<ProjectResponse> getApiProjects();

    @Override
    public void setIsErrorVisible() {
        boolean value = mProjects.getValue() == null || mProjects.getValue().size() == 0;
        getIsErrorVisible().postValue(value);
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public LiveData<PagedList<RichProject>> getProjects() {
        setIsErrorVisible();
        return mProjects;
    }
}
