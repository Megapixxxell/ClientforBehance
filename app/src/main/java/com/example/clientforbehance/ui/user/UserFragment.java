package com.example.clientforbehance.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.databinding.UserBinding;
import com.example.clientforbehance.ui.projects.ProjectFragment;

import javax.inject.Inject;

import toothpick.Toothpick;

public class UserFragment extends Fragment {

    @Inject
    UserViewModel mUserViewModel;
    private UserBinding mUserBinding;

    public static UserFragment newInstance(Bundle args) {
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mUserBinding = UserBinding.inflate(inflater, container, false);
        return mUserBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String usernameStr = "";
        if (getArguments() != null) {
            usernameStr = getArguments().getString(ProjectFragment.USERNAME_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(usernameStr);
        }

        Toothpick.inject(this, AppDelegate.getAppScope());
        mUserBinding.setVm(mUserViewModel);
        mUserViewModel.loadUser(usernameStr);
    }

    @Override
    public void onDetach() {
        mUserViewModel.dispatchDetach();
        super.onDetach();
    }
}
