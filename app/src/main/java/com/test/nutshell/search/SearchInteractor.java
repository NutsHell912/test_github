package com.test.nutshell.search;

import android.support.annotation.NonNull;

import com.test.nutshell.base.BaseInteractor;
import com.test.nutshell.data.db.Storage;
import com.test.nutshell.data.model.Repo;
import com.test.nutshell.data.network.ApiServiceHelper;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Consumer;

public class SearchInteractor extends BaseInteractor implements SearchMVP.Interactor {
    private List<Repo> repos;
    private final ApiServiceHelper apiServiceHelper;
    private final Storage storage;


    @Inject
    public SearchInteractor(@NonNull ApiServiceHelper apiServiceHelper, @NonNull Storage storage) {
        super();
        this.apiServiceHelper = apiServiceHelper;
        this.storage = storage;
        this.repos = new ArrayList<Repo>();
    }

    @NonNull
    @Override
    public Repo getRepo(int position) {
        return this.repos.get(position);
    }

    @NonNull
    @Override
    public Completable loadNewRepos(@NonNull String query) {
        storage.putQuery(query);
        return apiServiceHelper.getRepos(query)
                .doOnSuccess(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(@NonNull List<Repo> reposs) throws Exception {
                        storage.clearRepos();
                        repos = reposs;
                        storage.putRepos(repos);

                    }
                })
                .toCompletable();
    }

    @Override
    public void logout() {
        storage.putUser(null);
    }

    @Override
    public void restoreState() {
        repos = storage.getRepos();
    }

    @NonNull
    @Override
    public Completable loadNextRepos() {
        if (repos.size() > 0) {
            int page = (repos.size() - 1) / 30 + 1;
            return apiServiceHelper.getRepos(storage.getQuery(), page + 1)
                    .doOnSuccess(new Consumer<List<Repo>>() {
                        @Override
                        public void accept(@NonNull List<Repo> reps) throws Exception {
                            storage.putRepos(reps);
                            repos.addAll(reps);
                        }
                    })
                    .toCompletable();
        } else {
            return Completable.complete();
        }
    }

    @NonNull
    @Override
    public Completable loadCurrentRepos() {
        repos = storage.getRepos();
        if (repos.size() > 0) {
            return Completable.complete();
        } else {
            String query = storage.getQuery();
            if(query != null) {
                return loadNewRepos(query);
            } else {
                return Completable.complete();
            }
        }
    }

    @Override
    public void clearRepos() {
        storage.clearRepos();
        repos.clear();
    }

    @Override
    public int getRepoCount() {
        return repos.size();
    }

    //TODO сделать проверку с запросом на сервер или св-во isLoginIn
    @Override
    public boolean isLogin() {
        return storage.getUser() != null && !storage.getUser().getPassword().equals("")
                && !storage.getUser().getName().equals("");
    }
}
