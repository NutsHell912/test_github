package com.test.nutshell.data.network;

import com.test.nutshell.data.model.Repo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public final class ApiServiceHelper {
    private final ApiService apiService;

    @NotNull
    public Completable signIn() {
        return this.apiService.signIn();
    }

    @NotNull
    public final Single<List<Repo>> getRepos(String query) {
        return getRepos(query, 1);
    }

    @NotNull
    public final Single<List<Repo>> getRepos(String query, int page) {
        return apiService.getRepos(query, page)
                .map(new Function<RepoDtoFull, List<Repo>>() {
                    @Override
                    public List<Repo> apply(@NonNull RepoDtoFull repoDtofull) throws Exception {
                        List<Repo> repos = new ArrayList<>();
                        List<RepoDto> repoDtos = repoDtofull.getItems();
                        for (RepoDto repoDto : repoDtos) {
                            repos.add(new Repo(repoDto));
                        }
                        return repos;
                    }
                });
    }


    @Inject
    public ApiServiceHelper(@NotNull ApiService apiService) {
        this.apiService = apiService;
    }
}
