package com.example.clientforbehance;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.clientforbehance.data.database.BehanceDatabase;
import com.example.clientforbehance.data.model.Storage;

public class AppDelegate extends Application {

    private Storage mStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        final BehanceDatabase behanceDatabase = Room.databaseBuilder(this, BehanceDatabase.class,
                "behance database").fallbackToDestructiveMigration().build();

        mStorage = new Storage(behanceDatabase.getBehanceDao());
    }

    public Storage getStorage () {
        return  mStorage;
    }
}