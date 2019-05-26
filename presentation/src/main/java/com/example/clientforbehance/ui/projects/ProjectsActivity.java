package com.example.clientforbehance.ui.projects;

import android.support.v4.app.Fragment;

import com.example.clientforbehance.common.SingleFragmentActivity;

public class ProjectsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return ProjectFragment.newInstance();
    }
}
