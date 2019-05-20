package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.comments.CommentsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {CommentModule.class})
public interface CommentComponent {
    void inject(CommentsFragment fragment);

}
