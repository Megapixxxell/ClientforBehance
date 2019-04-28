package com.example.clientforbehance.data.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserResponse {

    @SerializedName("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
