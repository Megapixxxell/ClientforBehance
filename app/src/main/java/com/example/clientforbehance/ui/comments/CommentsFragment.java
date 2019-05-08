package com.example.clientforbehance.ui.comments;

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

public class CommentsFragment extends Fragment {

    private int mProjectId;

    private CommentsViewModel mCommentsViewModel;

    public static CommentsFragment newInstance(Bundle args) {
        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mCommentsViewModel = new CommentsViewModel(storage);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CommentsBindingg commentsBindingg = CommentsBindingg.inflate(inflater, container, false);
        commentsBindingg.setViewModel(mCommentsViewModel);
        return commentsBindingg.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mProjectId = getArguments().getInt(ProjectFragment.PROJECT_ID);
        }
        if (getActivity() != null) {
            getActivity().setTitle(R.string.comments);
        }
        mCommentsViewModel.loadComments(mProjectId);

    }

    @Override
    public void onDetach() {
        mCommentsViewModel.dispatchDetach();
        super.onDetach();
    }

}
