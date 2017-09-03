package com.test.nutshell.search;

import android.support.annotation.NonNull;

import com.test.nutshell.base.MvpInteractor;
import com.test.nutshell.base.MvpPresenter;
import com.test.nutshell.base.MvpView;
import com.test.nutshell.data.model.Repo;
import com.test.nutshell.di.PerActivity;


import io.reactivex.Completable;

public interface SearchMVP {
    public interface Interactor extends MvpInteractor {
        int getRepoCount();


        void restoreState();
        @NonNull
        Repo getRepo(int position);

        @NonNull
        Completable loadNextRepos();

        @NonNull
        Completable loadCurrentRepos();

        @NonNull
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

        void bindItemView(@NonNull SearchAdapter.ViewHolder holder, int position);

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

        void showError(@NonNull String msg);
    }

    public interface ItemView {
        void renderFullName(@NonNull String fullName);
        void renderDescription(@NonNull String description);

        void renderAvatart(@NonNull String url);
    }
}
