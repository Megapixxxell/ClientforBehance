package com.example.clientforbehance.ui.user;

import com.example.clientforbehance.common.BaseView;
import com.example.domain.model.user.User;

public interface UserView extends BaseView {

    void showUser(User user);
}
