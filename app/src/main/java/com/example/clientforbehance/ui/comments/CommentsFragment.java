package com.example.clientforbehance.ui.comments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.R;
import com.example.clientforbehance.databinding.CommentsBindingg;
import com.example.clientforbehance.ui.projects.ProjectFragment;

import javax.inject.Inject;

import toothpick.Toothpick;

public class CommentsFragment extends Fragment {

    private int mProjectId;
    @Inject
    CommentsViewModel mCommentsViewModel;
    private CommentsBindingg mCommentsBindingg;

    public static CommentsFragment newInstance(Bundle args) {
        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommentsViewModel = new CommentsViewModel();
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
        if (getArguments() != null) {
            mProjectId = getArguments().getInt(ProjectFragment.PROJECT_ID);
        }
        if (getActivity() != null) {
            getActivity().setTitle(R.string.comments);
        }
        Toothpick.inject(this, AppDelegate.getAppScope());
        mCommentsBindingg.setViewModel(mCommentsViewModel);
        mCommentsViewModel.loadComments(mProjectId);

    }

    @Override
    public void onDetach() {
        mCommentsViewModel.dispatchDetach();
        super.onDetach();
    }
}
