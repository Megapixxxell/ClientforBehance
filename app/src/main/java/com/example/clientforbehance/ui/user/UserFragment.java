package com.example.clientforbehance.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientforbehance.R;
import com.example.clientforbehance.common.PresenterFragment;
import com.example.clientforbehance.common.RefreshOwner;
import com.example.clientforbehance.common.Refreshable;
import com.example.clientforbehance.data.model.Storage;
import com.example.clientforbehance.data.model.user.User;
import com.example.clientforbehance.ui.projects.ProjectFragment;
import com.example.clientforbehance.utils.DateUtils;
import com.squareup.picasso.Picasso;


public class UserFragment extends PresenterFragment<UserPresenter> implements Refreshable, UserView {

    private ImageView mUserImage;
    private TextView mUsername, mUserCreatedOn, mUserLocation, mName;

    private View mUserView, mErrorView;

    private String mUsernameStr;

    private RefreshOwner mRefreshOwner;
    private UserPresenter mUserPresenter;
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
        mUserPresenter = new UserPresenter(mStorage, this);
        onRefreshData();
    }

    @Override
    public void onRefreshData() {
        mUserPresenter.getUser(mUsernameStr);

    }

    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;
        super.onDetach();
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
        mUserView.setVisibility(View.GONE);
    }

    @Override
    protected UserPresenter getPresenter() {
        return mUserPresenter;
    }

    @Override
    public void showUser(User user) {
        mErrorView.setVisibility(View.GONE);
        mUserView.setVisibility(View.VISIBLE);

        Picasso.with(getContext()).load(user.getImage().getPhotoUrl()).fit().into(mUserImage);
        mUsername.setText(user.getDisplayName());
        mName.setText(user.getUsername());
        mUserCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
        mUserLocation.setText(user.getLocation());
    }
}
