package com.example.clientforbehance.ui.comments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.R;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.databinding.CommentsBindingg;
import com.example.clientforbehance.ui.projects.ProjectFragment;
import com.example.clientforbehance.utils.CustomCommentsViewModelFactory;

public class CommentsFragment extends Fragment {

    private Storage mStorage;
    private CommentsBindingg mCommentsBindingg;

    public static CommentsFragment newInstance(Bundle args) {
        CommentsFragment fragment = new CommentsFragment();
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
        mCommentsBindingg = CommentsBindingg.inflate(inflater, container, false);

        return mCommentsBindingg.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int projectId = 0;
        if (getArguments() != null) {
            projectId = getArguments().getInt(ProjectFragment.PROJECT_ID);
        }
        if (getActivity() != null) {
            getActivity().setTitle(R.string.comments);
        }

        CustomCommentsViewModelFactory factory = new CustomCommentsViewModelFactory(mStorage, projectId);
        CommentsViewModel commentsViewModel = ViewModelProviders.of(this, factory).get(CommentsViewModel.class);
        mCommentsBindingg.setViewModel(commentsViewModel);
        mCommentsBindingg.setLifecycleOwner(this);
    }
}
