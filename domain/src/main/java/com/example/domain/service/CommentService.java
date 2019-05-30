package com.example.domain.service;

import com.example.domain.model.comment.Comment;

import java.util.List;

import io.reactivex.Single;

public interface CommentService {

    Single<List<Comment>> getComments(int projectId);
}
