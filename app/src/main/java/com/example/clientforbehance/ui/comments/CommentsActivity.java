package com.example.clientforbehance.ui.comments;

import android.support.v4.app.Fragment;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.common.SingleFragmentActivity;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.ui.projects.ProjectFragment;

public class CommentsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return CommentsFragment.newInstance(getIntent().getBundleExtra(ProjectFragment.ARGS_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}
