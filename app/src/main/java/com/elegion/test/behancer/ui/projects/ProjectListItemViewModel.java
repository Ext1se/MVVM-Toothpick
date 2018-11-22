package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.data.model.project.RichProject;
import com.elegion.test.behancer.utils.DateUtils;

public class ProjectListItemViewModel {

    private static final int FIRST_OWNER_INDEX = 0;

    private String mImageUrl;
    private String mName;
    private String mUsername;
    private String mPublishedOn;

    public ProjectListItemViewModel(RichProject item) {
        mImageUrl = item.mProject.getCover().getPhotoUrl();
        mName = item.mProject.getName();
        mPublishedOn = DateUtils.format(item.mProject.getPublishedOn());
        if (item.mOwners != null && item.mOwners.size() != 0) {
            mUsername = item.mOwners.get(FIRST_OWNER_INDEX).getUsername();
        }
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPublishedOn() {
        return mPublishedOn;
    }
}
