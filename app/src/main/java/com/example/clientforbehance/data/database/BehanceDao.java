package com.example.clientforbehance.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.project.Owner;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.data.model.project.RichProject;
import com.example.clientforbehance.data.model.user.Image;
import com.example.clientforbehance.data.model.user.User;

import java.util.List;

@Dao
public interface BehanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwners(List<Owner> owners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComments(List<Comment> comments);

    @Query("select * from project")
    List<Project> getProjects();

    @Query("select * from project order by published_on desc")
    DataSource.Factory<Integer, RichProject> getPagedProjects();

    @Query("select * from owner where project_id = :projectId")
    List<Owner> getOwnersFromProject(int projectId);

    @Query("select * from user where username = :userName")
    LiveData<User> getUserByNameLive(String userName);

    @Query("select * from user")
    LiveData<User> getLiveUser();

    @Query("select * from comment")
    List<Comment> getComments();

    @Query("select * from comment")
    DataSource.Factory<Integer, Comment> getPagedComments();

    @Query("select * from image")
    List<Image> getImages();

    @Query("delete from owner")
    void clearOwnerTable();

    @Query("delete from cover")
    void clearCoverTable();

    @Query("delete from project")
    void clearProjectTable();

    @Query("delete from stats")
    void clearStatsTable();

    @Query("delete from comment")
    void clearCommentTable();

}
