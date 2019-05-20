package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.user.UserView;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private UserView mUserView;

    public UserModule(UserView userView) {
        mUserView = userView;
    }

    @Provides
    UserView provideUserView() {
        return mUserView;
    }
}
