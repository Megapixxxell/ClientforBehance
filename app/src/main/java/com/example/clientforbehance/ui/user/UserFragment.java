package com.example.clientforbehance.ui.user;

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

public class UserFragment extends Fragment {

    private UserViewModel mUserViewModel;

    public static UserFragment newInstance(Bundle args) {
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Storage storage = context instanceof Storage.StorageOwner ? ((Storage.StorageOwner) context).obtainStorage() : null;
        mUserViewModel = new UserViewModel(storage);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserBinding userBinding = UserBinding.inflate(inflater, container, false);
        userBinding.setVm(mUserViewModel);
        return userBinding.getRoot();
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

        mUserViewModel.loadUser(usernameStr);
    }

    @Override
    public void onDetach() {
        mUserViewModel.dispatchDetach();
        super.onDetach();
    }
}
