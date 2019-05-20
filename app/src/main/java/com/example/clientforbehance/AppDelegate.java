package com.example.clientforbehance;

import android.app.Application;

import com.example.clientforbehance.dagger2.AppComponent;
import com.example.clientforbehance.dagger2.AppModule;
import com.example.clientforbehance.dagger2.DaggerAppComponent;
import com.example.clientforbehance.dagger2.NetworkModule;

public class AppDelegate extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}