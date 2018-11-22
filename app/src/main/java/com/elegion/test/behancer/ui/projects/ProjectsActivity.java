package com.elegion.test.behancer.ui.projects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.data.Storage;

public class ProjectsActivity extends SingleFragmentActivity implements Storage.StorageOwner {

    @Override
    protected Fragment getFragment() {
        return ProjectsFragment.newInstance();
    }

    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }
}
