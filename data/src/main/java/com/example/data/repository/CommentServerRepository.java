package com.example.data.repository;

import com.example.data.BuildConfig;
import com.example.data.api.BehanceApi;
import com.example.domain.model.comment.Comment;
import com.example.domain.model.comment.CommentResponse;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.ProjectResponse;
import com.example.domain.repository.CommentRepository;
import com.example.domain.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class CommentServerRepository implements CommentRepository {

    @Inject
    BehanceApi mBehanceApi;

    @Inject
    public CommentServerRepository() {
    }

    @Override
    public Single<List<Comment>> getComments(int projectId) {
        return mBehanceApi.getProjectComments(projectId).map(new Function<CommentResponse, List<Comment>>() {
            @Override
            public List<Comment> apply(CommentResponse commentResponse) throws Exception {
                return commentResponse.getComments();
            }
        });
    }

    @Override
    public void insertComments(List<Comment> comments, int projectId) {
        //do nothing
    }
}
