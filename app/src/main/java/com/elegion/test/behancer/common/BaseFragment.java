package com.elegion.test.behancer.common;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearDependencies();
    }

    abstract protected void injectDependencies();
    abstract protected void clearDependencies();
}
