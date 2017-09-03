package com.test.nutshell.data.network;

import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/user")
    @NonNull
    Completable signIn();

    @GET("/search/repositories")
    @NonNull
    Single<RepoDtoFull> getRepos(@Query("q") String query, @Query("page") int page);
}
