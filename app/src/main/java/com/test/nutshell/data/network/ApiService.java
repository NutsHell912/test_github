package com.test.nutshell.data.network;

import com.test.nutshell.data.model.Repo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/user")
    @NotNull
    Completable signIn();

    @GET("/search/repositories")
    @NotNull
    Single<RepoDtoFull> getRepos(@Query("q") String query, @Query("page") int page);
}
