package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.user.UserFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
    void inject(UserFragment fragment);

}
