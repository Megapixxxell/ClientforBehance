package com.example.clientforbehance.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.clientforbehance.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {


    private boolean mFragmentPopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        changeFragment(getFragment());
    }

    protected int getLayout() {
        return R.layout.ac_container;
    }

    protected abstract Fragment getFragment();

    public void changeFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        mFragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!mFragmentPopped) {
            FragmentTransaction ft = manager
                    .beginTransaction();
                    ft.replace(R.id.fragmentContainer, fragment, backStateName);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(backStateName);
                    ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1 || !mFragmentPopped){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }
}