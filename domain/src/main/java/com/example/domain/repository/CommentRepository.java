package com.example.domain.repository;

import com.example.domain.model.comment.Comment;

import java.util.List;

import io.reactivex.Single;

public interface CommentRepository {

    String SERVER = "SERVER";
    String DB = "DB";

    Single<List<Comment>> getComments(int projectId);
    void insertComments(List<Comment> comments, int projectId);
}
