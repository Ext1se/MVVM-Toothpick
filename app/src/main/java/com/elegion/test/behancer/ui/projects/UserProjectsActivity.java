package com.elegion.test.behancer.ui.projects;


import android.support.v4.app.Fragment;
import com.elegion.test.behancer.common.SingleFragmentActivity;

public class UserProjectsActivity extends SingleFragmentActivity {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return UserProjectsFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}
