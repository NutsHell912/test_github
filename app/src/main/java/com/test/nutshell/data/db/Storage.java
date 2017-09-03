package com.test.nutshell.data.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.test.nutshell.data.model.Repo;
import com.test.nutshell.data.model.User;

import java.util.List;

import javax.inject.Inject;

public class Storage {
    @NonNull
    private RepoDatabase repoDatabase;
    @NonNull
    private SharedPreferncesHelper preferncesHelper;

    @NonNull
    private Gson gson;

    @Inject
    public Storage(@NonNull RepoDatabase repoDatabase,
                   @NonNull SharedPreferncesHelper preferncesHelper, @NonNull Gson gson) {
        this.repoDatabase = repoDatabase;
        this.preferncesHelper = preferncesHelper;
        this.gson = gson;
    }

    @NonNull
    public User getUser() {
        return preferncesHelper.getUser();
    }

    public void putUser(@Nullable User user) {
        if (user != null) {
            preferncesHelper.putUser(user);
        } else {
            preferncesHelper.putUser(new User("", ""));
        }
    }

    @NonNull
    public List<Repo> getRepos() {
        return repoDatabase.repoDao().getAll();
    }

    public void putRepos(@NonNull List<Repo> repos) {
        repoDatabase.repoDao().insertAll(repos.toArray(new Repo[repos.size()]));
    }

    public void clearRepos() {
        repoDatabase.repoDao().clearTable();

    }

    @Nullable
    public String getQuery() {
        return preferncesHelper.getQuery();
    }

    public void putQuery(@NonNull String query) {
        preferncesHelper.setQuery(query);
    }


}
