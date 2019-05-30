package com.example.domain.repository;

import com.example.domain.model.user.User;

public interface IUserDBRepository {

    User getUser(String username);
    void insertUser(User user);
}
