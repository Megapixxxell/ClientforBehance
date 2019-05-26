package com.example.clientforbehance.dagger2;

import com.example.data.BuildConfig;
import com.example.data.api.ApiKeyInterceptor;
import com.example.data.api.BehanceApi;
import com.example.data.repository.ProjectDBRepository;
import com.example.data.repository.ProjectServerRepository;
import com.example.domain.repository.ProjectRepository;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Named(ProjectRepository.SERVER)
    ProjectRepository provideProjectServerRepository() {
        return new ProjectServerRepository();
    }

    @Provides
    @Singleton
    @Named(ProjectRepository.DB)
    ProjectRepository provideProjectDBRepository() {
        return new ProjectDBRepository();
    }

}
