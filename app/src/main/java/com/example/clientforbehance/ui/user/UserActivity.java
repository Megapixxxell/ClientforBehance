package com.example.clientforbehance.ui.user;

import android.support.v4.app.Fragment;

import com.example.clientforbehance.common.SingleFragmentActivity;
import com.example.clientforbehance.ui.projects.ProjectFragment;

public class UserActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return UserFragment.newInstance(getIntent().getBundleExtra(ProjectFragment.ARGS_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}
