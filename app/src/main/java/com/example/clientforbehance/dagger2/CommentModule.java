package com.example.clientforbehance.dagger2;

import com.example.clientforbehance.ui.comments.CommentsView;

import dagger.Module;
import dagger.Provides;

@Module
public class CommentModule {

    private CommentsView mCommentsView;

    public CommentModule(CommentsView commentsView) {
        mCommentsView = commentsView;
    }

    @Provides
    CommentsView provideCommentsView() {
        return mCommentsView;
    }
}
