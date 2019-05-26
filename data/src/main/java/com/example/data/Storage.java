package com.example.data;


import com.example.data.database.BehanceDao;
import com.example.domain.model.comment.Comment;
import com.example.domain.model.comment.CommentResponse;
import com.example.domain.model.comment.UserComment;
import com.example.domain.model.project.Cover;
import com.example.domain.model.project.Owner;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.ProjectResponse;
import com.example.domain.model.project.Stats;
import com.example.domain.model.user.Image;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private BehanceDao mBehanceDao;
    private List<Cover> mCovers = new ArrayList<>();
    private List<Owner> mOwners = new ArrayList<>();
    private List<Stats> mStats = new ArrayList<>();


    public Storage (BehanceDao behanceDao) {
        mBehanceDao = behanceDao;
    }

    public void insertProjectsToBaseFromResponse (ProjectResponse projectResponse) {

        List<Project> projects = projectResponse.getProjects();

        getCoversOwnersStats(projects);

        mBehanceDao.insertProjects(projects);

        mBehanceDao.clearCoverTable();
        mBehanceDao.insertCovers(mCovers);

        mBehanceDao.clearOwnerTable();
        mBehanceDao.insertOwners(mOwners);

        mBehanceDao.clearStatsTable();
        mBehanceDao.insertStats(mStats);
    }

    public ProjectResponse getProjectResponseFromStorage() {
        List<Project> projects = mBehanceDao.getProjects();
        for (Project project : projects) {
            project.setCover(mBehanceDao.getCoverFromProject(project.getId()));
            project.setOwners(mBehanceDao.getOwnersFromProject(project.getId()));
        }

        ProjectResponse response = new ProjectResponse();
        response.setProjects(projects);

        return response;
    }

    private void getCoversOwnersStats(List<Project> projects) {
        for (int i = 0; i < projects.size(); i++) {

            int projectId = projects.get(i).getId();

            Cover cover = projects.get(i).getCover();
            cover.setId(i);
            cover.setProjectId(projectId);
            mCovers.add(cover);

            Owner owner = projects.get(i).getOwners().get(0);
            owner.setId(i);
            owner.setProjectId(projectId);
            mOwners.add(owner);

            Stats stats = projects.get(i).getStats();
            stats.setId(i);
            stats.setProjectId(projectId);
            mStats.add(stats);
        }
    }

    public void insertUser(UserResponse response) {
        User user = response.getUser();

        Image image = user.getImage();
        image.setId(user.getId());
        image.setUserId(user.getId());

        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);
    }

    public UserResponse getUser(String username) {
        User user = mBehanceDao.getUserByName(username);
        Image image = mBehanceDao.getImageFromUser(user.getId());
        user.setImage(image);

        UserResponse response = new UserResponse();
        response.setUser(user);

        return response;
    }

    public void insertCommentsToBaseFromResponse (CommentResponse commentResponse) {
        List<Comment> comments = commentResponse.getComments();

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

    public CommentResponse getCommentResponseFromStorage() {
        List<Comment> comments = mBehanceDao.getComments();
        for (Comment comment : comments) {
            comment.setUser(mBehanceDao.getUserFromComment(comment.getId()));
        }

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComments(comments);
        return commentResponse;
    }
}
