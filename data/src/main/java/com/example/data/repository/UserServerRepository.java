package com.example.data.repository;

import com.example.data.api.BehanceApi;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserResponse;
import com.example.domain.repository.IUserServerRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class UserServerRepository implements IUserServerRepository {

    @Inject
    BehanceApi mBehanceApi;

    @Inject
    UserServerRepository() {
    }

    @Override
    public Single<User> getUser(String username) {
        return mBehanceApi.getUser(username).map(new Function<UserResponse, User>() {
            @Override
            public User apply(UserResponse userResponse) {
                return userResponse.getUser();
            }
        });
    }
}
