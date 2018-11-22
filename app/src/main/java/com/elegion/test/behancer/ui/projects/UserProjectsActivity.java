package com.elegion.test.behancer.ui.projects;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.data.Storage;

public class UserProjectsActivity extends SingleFragmentActivity implements Storage.StorageOwner {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return UserProjectsFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }

    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }
}
