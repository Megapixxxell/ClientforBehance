package com.example.domain.service;

import com.example.domain.model.user.User;

import io.reactivex.Single;

public interface UserService {

    Single<User> getUser(String username);
}
