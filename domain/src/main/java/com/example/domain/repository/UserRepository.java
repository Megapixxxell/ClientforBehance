package com.example.domain.repository;

import com.example.domain.model.user.User;

import java.util.List;

import io.reactivex.Single;

public interface UserRepository {

    String SERVER = "SERVER";
    String DB = "DB";

    Single<User> getUser(String username);
    void insertUser(User user);
}
