package com.example.domain.service;

import com.example.domain.ApiUtils;
import com.example.domain.model.comment.Comment;
import com.example.domain.repository.ICommentDBRepository;
import com.example.domain.repository.ICommentServerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CommentServiceImpl implements CommentService {

    @Inject
    ICommentServerRepository mServerRepository;

    @Inject
    ICommentDBRepository mDBRepository;

    @Inject
    CommentServiceImpl() {
    }

    @Override
    public Single<List<Comment>> getComments(int projectId) {
        return mServerRepository.getComments(projectId)
                .doOnSuccess(response -> mDBRepository.insertComments(response, projectId))
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                        mDBRepository.getComments(projectId) : null);
    }

}
