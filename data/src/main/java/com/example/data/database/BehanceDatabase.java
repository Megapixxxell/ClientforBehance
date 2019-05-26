package com.example.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.domain.model.comment.Comment;
import com.example.domain.model.comment.UserComment;
import com.example.domain.model.project.Cover;
import com.example.domain.model.project.Owner;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.Stats;
import com.example.domain.model.user.Image;
import com.example.domain.model.user.User;


@Database(entities = {Project.class, Cover.class, Owner.class, Stats.class, User.class, Image.class, Comment.class, UserComment.class},
            version = 1)
public abstract class BehanceDatabase extends RoomDatabase {
    public abstract BehanceDao getBehanceDao();
}
