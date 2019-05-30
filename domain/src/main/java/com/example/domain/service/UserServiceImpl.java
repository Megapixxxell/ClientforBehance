package com.example.domain.service;

import com.example.domain.ApiUtils;
import com.example.domain.model.user.User;
import com.example.domain.repository.IUserDBRepository;
import com.example.domain.repository.IUserServerRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserServiceImpl implements UserService {

    @Inject
    IUserServerRepository mServerRepository;

    @Inject
    IUserDBRepository mDBRepository;

    @Inject
    UserServiceImpl() {
    }

    @Override
    public Single<User> getUser(String username) {
        return mServerRepository.getUser(username)
                .doOnSuccess(response -> mDBRepository.insertUser(response))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mDBRepository.getUser(username) : null);
    }
}
