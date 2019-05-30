package com.example.domain.repository;

import com.example.domain.model.user.User;

import io.reactivex.Single;

public interface IUserServerRepository {

    Single<User> getUser(String username);
}
