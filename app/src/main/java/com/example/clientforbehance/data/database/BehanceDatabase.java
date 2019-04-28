package com.example.clientforbehance.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.clientforbehance.data.model.comment.Comment;
import com.example.clientforbehance.data.model.comment.UserComment;
import com.example.clientforbehance.data.model.project.Cover;
import com.example.clientforbehance.data.model.project.Owner;
import com.example.clientforbehance.data.model.project.Project;
import com.example.clientforbehance.data.model.project.Stats;
import com.example.clientforbehance.data.model.user.Image;
import com.example.clientforbehance.data.model.user.User;


@Database(entities = {Project.class, Cover.class, Owner.class, Stats.class, User.class, Image.class, Comment.class, UserComment.class},
            version = 1)
public abstract class BehanceDatabase extends RoomDatabase {
    public abstract BehanceDao getBehanceDao();
}
