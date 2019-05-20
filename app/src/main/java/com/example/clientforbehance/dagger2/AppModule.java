package com.example.clientforbehance.dagger2;

import android.arch.persistence.room.Room;

import com.example.clientforbehance.AppDelegate;
import com.example.clientforbehance.data.database.BehanceDatabase;
import com.example.clientforbehance.data.model.Storage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AppDelegate mApp;

    public AppModule(AppDelegate app) {
        mApp = app;
    }

    @Provides
    @Singleton
    AppDelegate provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    Storage provideStorage() {

        final BehanceDatabase behanceDatabase = Room.databaseBuilder(mApp, BehanceDatabase.class,
                "behance database").fallbackToDestructiveMigration().build();

        return new Storage(behanceDatabase.getBehanceDao());
    }



}
