package com.example.clientforbehance.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.clientforbehance.R;

public abstract class RefreshActivity extends SingleFragmentActivity implements RefreshOwner,
        SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeRefreshLayout = findViewById(R.id.refresher);
        mSwipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    protected int getLayout() {
        return R.layout.ac_swipe_container;

    }

    @Override
    public void onRefresh() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof Refreshable) {
            ((Refreshable) fragment).onRefreshData();
        } else {
            setRefreshState(false);
        }
    }

    @Override
    public void setRefreshState(boolean refreshing) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(refreshing));
    }

}
