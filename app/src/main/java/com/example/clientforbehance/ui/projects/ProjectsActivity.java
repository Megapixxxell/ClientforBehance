package com.example.clientforbehance.ui.projects;

import android.support.v4.app.Fragment;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.common.SingleFragmentActivity;
import com.example.clientforbehance.data.model.Storage;

public class ProjectsActivity extends SingleFragmentActivity implements Storage.StorageOwner {
    @Override
    protected Fragment getFragment() {
        return ProjectFragment.newInstance();
    }

    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }
}
