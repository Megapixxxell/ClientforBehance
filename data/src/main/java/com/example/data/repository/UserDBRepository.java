package com.example.data.repository;

import com.example.data.database.BehanceDao;
import com.example.domain.model.user.Image;
import com.example.domain.model.user.User;
import com.example.domain.repository.IUserDBRepository;

import javax.inject.Inject;

public class UserDBRepository implements IUserDBRepository {

    @Inject
    BehanceDao mBehanceDao;

    @Inject
    UserDBRepository() {
    }

    @Override
    public User getUser(final String username) {
        User user = mBehanceDao.getUserByName(username);
        Image image = mBehanceDao.getImageFromUser(user.getId());
        user.setImage(image);
        return user;
    }

    @Override
    public void insertUser(User user) {
        Image image = user.getImage();
        image.setId(user.getId());
        image.setUserId(user.getId());
        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);
    }
}
