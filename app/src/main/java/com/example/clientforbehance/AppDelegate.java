package com.example.clientforbehance;

import android.app.Application;

import com.example.clientforbehance.toothpick.AppModule;
import com.example.clientforbehance.toothpick.NetworkModule;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class AppDelegate extends Application {

    private static Scope sAppScope;

    @Override
    public void onCreate() {
        super.onCreate();

        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());

        MemberInjectorRegistryLocator.setRootRegistry(new MemberInjectorRegistry());
        FactoryRegistryLocator.setRootRegistry(new FactoryRegistry());

        sAppScope = Toothpick.openScope(AppDelegate.class);
        sAppScope.installModules(new SmoothieApplicationModule(this), new NetworkModule(), new AppModule(this));
        Toothpick.inject(this, sAppScope);

    }

    public static Scope getAppScope() {
        return sAppScope;
    }
}