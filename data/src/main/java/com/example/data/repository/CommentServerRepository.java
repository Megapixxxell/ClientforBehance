package com.example.data.repository;

import com.example.data.api.BehanceApi;
import com.example.domain.model.comment.Comment;
import com.example.domain.model.comment.CommentResponse;
import com.example.domain.repository.ICommentServerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class CommentServerRepository implements ICommentServerRepository {

    @Inject
    BehanceApi mBehanceApi;

    @Inject
    CommentServerRepository() {
    }

    @Override
    public Single<List<Comment>> getComments(int projectId) {
        return mBehanceApi.getProjectComments(projectId).map(new Function<CommentResponse, List<Comment>>() {
            @Override
            public List<Comment> apply(CommentResponse commentResponse) {
                return commentResponse.getComments();
            }
        });
    }
}
