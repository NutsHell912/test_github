package com.test.nutshell.search;


import com.test.nutshell.base.BasePresenter;
import com.test.nutshell.data.model.Repo;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter<V extends SearchMVP.View, I extends SearchMVP.Interactor>
        extends BasePresenter<V, I> implements SearchMVP.Presenter<V, I> {

    private Disposable repoDisposable;
    private Disposable repoNextDisposable;


    @Inject
    public SearchPresenter(@NotNull I mvpInteractor) {
        super(mvpInteractor);
        this.repoDisposable = Disposables.disposed();
        this.repoNextDisposable = Disposables.disposed();
    }

    @Override
    public void onAttach(@NotNull V mvpView) {
        super.onAttach(mvpView);
        mvpView.setRefreshing(true);
        loadRepos();
    }

    @Override
    public void onDetach() {
        repoDisposable.dispose();
        repoNextDisposable.dispose();
        getMvpView().setRefreshing(false);
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        getInteractor().clearRepos();
        this.loadRepos();
    }

    @Override
    public int getRepoId(int position) {
        return (this.getInteractor()).getRepo(position).hashCode();
    }

    @Override
    public int getRepoCount() {
        return (this.getInteractor()).getRepoCount();
    }

    @Override
    public void bindItemView(@NotNull SearchAdapter.ViewHolder holder, int position) {
        Repo repo = (this.getInteractor()).getRepo(position);
        holder.renderFullName(repo.getFullName());
        holder.renderDescription(repo.getDescription());
        holder.renderAvatart(repo.getUrl());
    }

    @Override
    public boolean isLogin() {
        return getInteractor().isLogin();
    }

    @Override
    public void onSearch(String query) {
        SearchMVP.View view = getMvpView();
        if (view != null) {
            view.setRefreshing(true);
            if (!repoDisposable.isDisposed()) {
                repoDisposable.dispose();
            }
            repoDisposable = subscribe(getInteractor().loadNewRepos(query));
        }
    }

    @Override
    public void login() {
        if (getMvpView() != null) {
            getMvpView().navigateToStart();
        }
    }

    @Override
    public void logout() {
        if (getMvpView() != null) {
            getInteractor().logout();
            getMvpView().renderMenu();
        }
    }

    private void loadRepos() {
        SearchMVP.View view = getMvpView();
        if (view != null) {
            view.setRefreshing(true);
            if (!repoDisposable.isDisposed()) {
                repoDisposable.dispose();
            }
            repoDisposable = subscribe(getInteractor().loadCurrentRepos());

        }
    }

    @Override
    public void onNextRepoLoad() {
        SearchMVP.View view = getMvpView();
        if (view != null) {
            view.setRefreshing(true);
            if (!repoDisposable.isDisposed() || !repoNextDisposable.isDisposed()) {
                return;
            }

            repoNextDisposable = subscribe(getInteractor().loadNextRepos());
        }
    }

    private Disposable subscribe(Completable completable) {
        return completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                               @Override
                               public void run() throws Exception {
                                   SearchMVP.View view = getMvpView();
                                   if (view != null) {
                                       view.renderList();
                                       view.setRefreshing(false);
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                SearchMVP.View view = getMvpView();
                                if (view != null) {
                                    view.showError(throwable.getMessage());
                                    view.setRefreshing(false);
                                }
                            }
                        });
    }

}
