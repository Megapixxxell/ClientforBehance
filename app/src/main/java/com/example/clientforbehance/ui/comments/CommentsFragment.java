package com.example.clientforbehance.ui.comments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clientforbehance.R;
import com.example.clientforbehance.common.RefreshOwner;
import com.example.clientforbehance.common.Refreshable;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.ui.projects.ProjectFragment;
import com.example.clientforbehance.ui.projects.ProjectsAdapter;
import com.example.clientforbehance.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentsFragment extends Fragment implements Refreshable {

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private Storage mStorage;
    private CommentsAdapter mCommentsAdapter;
    private Disposable mDisposable;

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
        if (mDisposable != null) mDisposable.dispose();
        super.onDetach();
    }

    @Override
    public void onRefreshData() {
        getComments();
    }

    private void getComments() {
        mDisposable = ApiUtils.getApiService().getProjectComments(mProjectId)
                .doOnSuccess(commentResponse -> mStorage.insertCommentsToBaseFromResponse(commentResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getCommentResponseFromStorage() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mRefreshOwner.setRefreshState(false))
                .subscribe(response -> {
                            mErrorView.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            mCommentsAdapter.addData(response.getComments(), true);
                        },
                        throwable -> {
                            mErrorView.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        });
    }

}
