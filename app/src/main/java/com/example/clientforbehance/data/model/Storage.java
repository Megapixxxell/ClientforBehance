package com.example.clientforbehance.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.clientforbehance.data.database.BehanceDao;
import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.project.Owner;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.data.model.project.RichProject;
import com.example.clientforbehance.data.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int PAGE_SIZE = 10;
    private BehanceDao mBehanceDao;

    public Storage (BehanceDao behanceDao) {
        mBehanceDao = behanceDao;
    }

    public void insertProjectsToBaseFromList (List <Project> projects) {
        mBehanceDao.clearStatsTable();
        mBehanceDao.clearCoverTable();
        mBehanceDao.clearOwnerTable();
        mBehanceDao.clearProjectTable();
        mBehanceDao.insertProjects(projects);

        mBehanceDao.clearOwnerTable();
        mBehanceDao.insertOwners(getOwners(projects));
    }

    private List<Owner> getOwners(List<Project> projects) {
        List<Owner> owners = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {

            int projectId = projects.get(i).getId();

            Owner owner = projects.get(i).getOwners().get(0);
            owner.setId(i);
            owner.setProjectId(projectId);
            owners.add(owner);
        }
        return owners;
    }

    public LiveData<PagedList<RichProject>> getPagedProjects () {
        return new LivePagedListBuilder<>(mBehanceDao.getPagedProjects(), PAGE_SIZE).build();
    }

    public void insertUser(User user) {
        mBehanceDao.insertUser(user);
    }

    public LiveData<User> getLiveUserByName(String username) {
        return mBehanceDao.getUserByNameLive(username);
    }

    public void insertCommentsToBaseFromList(List<Comment> comments) {
        mBehanceDao.clearCommentTable();
        mBehanceDao.insertComments(comments);
    }

    public LiveData<PagedList<Comment>> getPagedComments () {
        return new LivePagedListBuilder<>(mBehanceDao.getPagedComments(), PAGE_SIZE).build();
    }

    public interface StorageOwner {
        Storage obtainStorage();
    }
}
