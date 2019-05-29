package com.example.data.repository;

import com.example.data.database.BehanceDao;
import com.example.domain.model.comment.Comment;
import com.example.domain.model.comment.CommentResponse;
import com.example.domain.model.comment.UserComment;
import com.example.domain.model.project.Cover;
import com.example.domain.model.project.Owner;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.Stats;
import com.example.domain.repository.CommentRepository;
import com.example.domain.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class CommentDBRepository implements CommentRepository {

    @Inject
    BehanceDao mBehanceDao;

    @Inject
    public CommentDBRepository() {
    }

    @Override
    public Single<List<Comment>> getComments(final int projectId) {
        return Single.fromCallable(new Callable<List<Comment>>() {
            @Override
            public List<Comment> call() throws Exception {
                List<Comment> comments = mBehanceDao.getCommentsProjectId(projectId);
                for (Comment comment : comments) {
                    comment.setUser(mBehanceDao.getUserFromComment(comment.getId()));
                }
                return comments;
            }
        });
    }

    @Override
    public void insertComments(List<Comment> comments, int projectId) {
        for (Comment comment : comments) {
            comment.setProjectId(projectId);
        }

        mBehanceDao.insertComments(comments);

        mBehanceDao.insertUsers(getUsersFromComments(comments));
    }


    public List<UserComment> getUsersFromComments (List<Comment> comments) {
        List<UserComment> users = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            UserComment u = comments.get(i).getUser();
            u.setCommentId(comments.get(i).getId());
            users.add(u);
        }

        return users;
    }

}
