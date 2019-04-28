package com.example.clientforbehance.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.comment.UserComment;
import com.example.clientforbehance.data.model.project.Cover;
import com.example.clientforbehance.data.model.project.Owner;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.data.model.project.Stats;
import com.example.clientforbehance.data.model.user.Image;
import com.example.clientforbehance.data.model.user.User;

import java.util.List;

@Dao
public interface BehanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCovers(List<Cover> covers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwners(List<Owner> owners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStats(List<Stats> stats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Image image);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComments(List<Comment> comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<UserComment> users);

    @Query("select * from project")
    List<Project> getProjects();

    @Query("select * from cover where project_id = :projectId")
    Cover getCoverFromProject(int projectId);

    @Query("select * from owner where project_id = :projectId")
    List<Owner> getOwnersFromProject(int projectId);

    @Query("select * from stats where project_id = :projectId")
    Stats getStatsFromProject(int projectId);

    @Query("select * from user where username = :userName")
    User getUserByName(String userName);

    @Query("select * from image where user_id = :userId")
    Image getImageFromUser(int userId);

    @Query("select * from usercomment where comment_id = :commentId")
    UserComment getUserFromComment(int commentId);

    @Query("select * from comment")
    List<Comment> getComments();

    @Query("select * from usercomment")
    List<UserComment> getUsers();

    @Query("select * from image")
    List<Image> getImages();

    @Query("delete from owner")
    void clearOwnerTable();

    @Query("delete from cover")
    void clearCoverTable();

    @Query("delete from stats")
    void clearStatsTable();

    @Query("delete from image")
    void clearImageTable();

}
