package com.example.clientforbehance.ui.comments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.clientforbehance.R;
import com.example.clientforbehance.common.PresenterFragment;
import com.example.clientforbehance.common.RefreshOwner;
import com.example.clientforbehance.common.Refreshable;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.ui.projects.ProjectFragment;

import java.util.List;

public class CommentsFragment extends PresenterFragment implements Refreshable, CommentsView {

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private Storage mStorage;
    private CommentsAdapter mCommentsAdapter;
    @InjectPresenter
    CommentsPresenter mCommentsPresenter;

    @ProvidePresenter
    CommentsPresenter provideCommentsPresenter() {
        return new CommentsPresenter(mStorage, this);

    }

    private int mProjectId;

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

        if (context instanceof RefreshOwner) {
            mRefreshOwner = (RefreshOwner) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_comments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_comments);
        mErrorView = view.findViewById(R.id.errorView);
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

        mCommentsAdapter = new CommentsAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mCommentsAdapter);
        onRefreshData();
    }

    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;
        super.onDetach();
    }

    @Override
    public void onRefreshData() {
        mCommentsPresenter.getComments(mProjectId);
    }

    @Override
    public void showRefresh() {
        mRefreshOwner.setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    protected CommentsPresenter getPresenter() {
        return mCommentsPresenter;
    }

    @Override
    public void showComments(List<Comment> comments) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mCommentsAdapter.addData(comments, true);
    }
}
