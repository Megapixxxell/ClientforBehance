package com.example.domain.repository;

import com.example.domain.model.comment.Comment;

import java.util.List;

import io.reactivex.Single;

public interface ICommentServerRepository {

    Single<List<Comment>> getComments(int projectId);
}
