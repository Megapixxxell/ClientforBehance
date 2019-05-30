package com.example.data.repository;

import com.example.data.database.BehanceDao;
import com.example.domain.model.comment.Comment;
import com.example.domain.model.comment.UserComment;
import com.example.domain.repository.ICommentDBRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CommentDBRepository implements ICommentDBRepository {

    @Inject
    BehanceDao mBehanceDao;

    @Inject
    CommentDBRepository() {
    }

    @Override
    public List<Comment> getComments(final int projectId) {
        List<Comment> comments = mBehanceDao.getCommentsProjectId(projectId);
        for (Comment comment : comments) {
            comment.setUser(mBehanceDao.getUserFromComment(comment.getId()));
        }
        return comments;
    }

    @Override
    public void insertComments(List<Comment> comments, int projectId) {
        for (Comment comment : comments) {
            comment.setProjectId(projectId);
        }
        mBehanceDao.insertComments(comments);
        mBehanceDao.insertUsers(getUsersFromComments(comments));
    }

    public List<UserComment> getUsersFromComments(List<Comment> comments) {
        List<UserComment> users = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            UserComment u = comments.get(i).getUser();
            u.setCommentId(comments.get(i).getId());
            users.add(u);
        }
        return users;
    }
}
