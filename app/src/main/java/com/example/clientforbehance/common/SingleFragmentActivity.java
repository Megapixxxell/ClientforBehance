package com.example.clientforbehance.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.clientforbehance.R;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.AppDelegate;

public abstract class SingleFragmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());


        if (savedInstanceState == null) {
            changeFragment(getFragment());
        }
    }

    protected int getLayout() {
        return R.layout.ac_container;
    }

    protected abstract Fragment getFragment();


    public void changeFragment(Fragment fragment) {
        boolean addToBackStack = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) != null;

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }


}