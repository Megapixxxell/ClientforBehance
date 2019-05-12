package com.example.clientforbehance.ui.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.databinding.UserBinding;
import com.example.clientforbehance.ui.projects.ProjectFragment;
import com.example.clientforbehance.utils.CustomUserViewModelFactory;

public class UserFragment extends Fragment {

    private Storage mStorage;
    private UserBinding mUserBinding;

    public static UserFragment newInstance(Bundle args) {
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            mStorage = ((Storage.StorageOwner) context).obtainStorage();
        }
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

        CustomUserViewModelFactory factory = new CustomUserViewModelFactory(mStorage, usernameStr);
        UserViewModel userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        mUserBinding.setVm(userViewModel);
        mUserBinding.setLifecycleOwner(this);
    }
}
