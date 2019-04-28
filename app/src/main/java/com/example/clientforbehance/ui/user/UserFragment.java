package com.example.clientforbehance.ui.user;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientforbehance.R;
import com.example.clientforbehance.common.RefreshOwner;
import com.example.clientforbehance.common.Refreshable;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.user.User;
import com.example.clientforbehance.ui.comments.CommentsAdapter;
import com.example.clientforbehance.ui.projects.ProjectFragment;
import com.example.clientforbehance.utils.ApiUtils;
import com.example.clientforbehance.utils.DateUtils;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserFragment extends Fragment implements Refreshable {

    private ImageView mUserImage;
    private TextView mUsername, mUserCreatedOn, mUserLocation, mName;

    private View mUserView, mErrorView;

    private String mUsernameStr;

    private RefreshOwner mRefreshOwner;
    private Disposable mDisposable;
    private Storage mStorage;

    public static UserFragment newInstance(Bundle args) {

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mStorage = context instanceof Storage.StorageOwner ? ((Storage.StorageOwner) context).obtainStorage() : null;
        mRefreshOwner = context instanceof RefreshOwner ? (RefreshOwner) context : null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_user, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserImage = view.findViewById(R.id.iv_user);
        mName = view.findViewById(R.id.tv_username_details);
        mUsername = view.findViewById(R.id.tv_display_name_details);
        mUserCreatedOn = view.findViewById(R.id.tv_created_on_details);
        mUserLocation = view.findViewById(R.id.tv_location_details);

        mUserView = view.findViewById(R.id.view_user);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mUsernameStr = getArguments().getString(ProjectFragment.USERNAME_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mUsernameStr);
        }

        mUserView.setVisibility(View.VISIBLE);

        onRefreshData();
    }

    @Override
    public void onRefreshData() {
        getUser();

    }

    private void getUser() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsernameStr)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(userResponse -> mStorage.insertUser(userResponse))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mStorage.getUser(mUsernameStr) : null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mRefreshOwner.setRefreshState(false))
                .subscribe(userResponse -> {
                    mErrorView.setVisibility(View.GONE);
                    mUserView.setVisibility(View.VISIBLE);
                    bind(userResponse.getUser());
                }, throwable -> {
                    mErrorView.setVisibility(View.VISIBLE);
                    mUserView.setVisibility(View.GONE);
                });
    }

    private void bind(User user) {
        Picasso.with(getContext()).load(user.getImage().getPhotoUrl()).fit().into(mUserImage);
        mUsername.setText(user.getDisplayName());
        mName.setText(user.getUsername());
        mUserCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
        mUserLocation.setText(user.getLocation());
    }

    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;
        if (mDisposable != null) mDisposable.dispose();
        super.onDetach();
    }
}
