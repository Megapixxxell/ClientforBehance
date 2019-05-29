package com.example.clientforbehance.dagger2;

import android.arch.persistence.room.Room;

import com.example.clientforbehance.AppDelegate;
import com.example.data.database.BehanceDao;
import com.example.data.database.BehanceDatabase;

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
    BehanceDatabase provideDataBase() {
        return Room.databaseBuilder(mApp, BehanceDatabase.class,
                "behance database").fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    BehanceDao provideBehanceDao(BehanceDatabase database) {
        return database.getBehanceDao();
    }

    @Provides
    @Singleton
    Storage provideStorage(BehanceDao behanceDao) {
        return new Storage(behanceDao);
    }



}
