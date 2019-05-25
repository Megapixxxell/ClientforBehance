package com.example.clientforbehance.toothpick;

import android.arch.persistence.room.Room;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.data.database.BehanceDatabase;
import com.example.clientforbehance.data.model.Storage;

import toothpick.config.Module;

public class AppModule extends Module {

    private final AppDelegate mApp;

    public AppModule(AppDelegate app) {
        mApp = app;
        bind(AppDelegate.class).toInstance(mApp);
        bind(Storage.class).toInstance(provideStorage());

    }

    Storage provideStorage () {
        final BehanceDatabase behanceDatabase = Room.databaseBuilder(mApp, BehanceDatabase.class,
                "behance database").fallbackToDestructiveMigration().build();

        return new Storage(behanceDatabase.getBehanceDao());
    }

    public AppDelegate getApp() {
        return mApp;
    }
}
