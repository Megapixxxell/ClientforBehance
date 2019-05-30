package com.example.domain.repository;

import com.example.domain.model.comment.Comment;

import java.util.List;

public interface ICommentDBRepository {

    List<Comment> getComments(int projectId);
    void insertComments(List<Comment> comments, int projectId);
}
