package com.example.clientforbehance.toothpick;

import com.example.clientforbehance.BuildConfig;
import com.example.clientforbehance.data.api.ApiKeyInterceptor;
import com.example.clientforbehance.data.api.BehanceApi;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;

public class NetworkModule extends Module {

    private final Gson mGson = new Gson();
    private final OkHttpClient mClient = getClient();
    private final Retrofit mRetrofit = getRetrofit();

    public NetworkModule() {
        bind(Gson.class).toInstance(mGson);
        bind(OkHttpClient.class).toInstance(mClient);
        bind(Retrofit.class).toInstance(mRetrofit);
        bind(BehanceApi.class).toInstance(getApiService());
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new ApiKeyInterceptor());
        if (!BuildConfig.BUILD_TYPE.contains("release")) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private BehanceApi getApiService() {
            return mRetrofit.create(BehanceApi.class);
    }
}
