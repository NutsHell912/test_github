package com.test.nutshell.search;

import com.test.nutshell.base.MvpInteractor;
import com.test.nutshell.base.MvpPresenter;
import com.test.nutshell.base.MvpView;
import com.test.nutshell.data.model.Repo;
import com.test.nutshell.di.PerActivity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Completable;

public interface SearchMVP {
    public interface Interactor extends MvpInteractor {
        int getRepoCount();


        void restoreState();
        @NotNull
        Repo getRepo(int position);

        @NotNull
        Completable loadNextRepos();

        @NotNull
        Completable loadCurrentRepos();

        @NotNull
        Completable loadNewRepos(String query);

        void logout();

        void clearRepos();


        boolean isLogin();
    }

    @PerActivity
    public interface Presenter<V extends View, I extends Interactor> extends MvpPresenter<V, I> {
        void onRefresh();
        boolean isLogin();
        void onNextRepoLoad();

        void bindItemView(@NotNull SearchAdapter.ViewHolder holder, int position);

        int getRepoId(int position);

        int getRepoCount();

        void onSearch(String query);

        void login();

        void logout();
    }

    public interface View extends MvpView {

        void renderMenu();

        void navigateToStart();

        void setRefreshing(boolean isRefreshing);

        void renderList();

        void showError(@NotNull String msg);
    }

    public interface ItemView {
        void renderFullName(@NotNull String fullName);
        void renderDescription(@NotNull String description);

        void renderAvatart(@NotNull String url);
    }
}
