package com.test.nutshell.di.module;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.nutshell.data.db.RepoDatabase;
import com.test.nutshell.data.db.Storage;
import com.test.nutshell.data.model.User;
import com.test.nutshell.data.network.ApiService;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class ServiceModule {
    @Provides
    @Singleton
    @NonNull
    public final Gson provideGson() {
        return new GsonBuilder()
                .enableComplexMapKeySerialization()
                .create();
    }

    @Provides
    @Singleton
    @NonNull
    public final OkHttpClient provideHttpClient(@NonNull final Storage storage) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        User user = storage.getUser();
                        if (!user.getName().equals("") && !user.getPassword().equals("")) {
                            String credentials = Credentials.basic(user.getName(),
                                    user.getPassword());
                            Request authenticatedRequest = request.newBuilder()
                                    .header("Authorization", credentials).build();
                            return chain.proceed(authenticatedRequest);
                        }
                        return chain.proceed(request);
                    }
                }
        );
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        return builder.build();
    }

    @Provides
    @Singleton
    @NonNull
    public final ApiService provideApiService(@NonNull OkHttpClient httpClient, @NonNull Gson gson) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService.class);
    }

    @Provides
    @NonNull
    @Singleton
    public final SharedPreferences provideSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    @Provides
    @NonNull
    @Singleton
    public final RepoDatabase provideRepoDatabase(@NonNull Context context) {
        return Room.databaseBuilder(context, RepoDatabase.class, RepoDatabase.DATABASE_NAME)
        .allowMainThreadQueries().build();
    }

}
