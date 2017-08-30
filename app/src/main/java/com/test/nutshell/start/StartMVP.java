package com.test.nutshell.start;


import android.support.annotation.NonNull;

import com.test.nutshell.base.MvpInteractor;
import com.test.nutshell.base.MvpPresenter;
import com.test.nutshell.base.MvpView;
import com.test.nutshell.di.PerActivity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Completable;

public class StartMVP {
    public interface Interactor extends MvpInteractor {
        Completable signIn();
        void onLoginChaned(@NonNull String login);
        void onPasswordChanged(@NonNull String password);
        @NonNull String getLogin();
        @NonNull String getPassword();
        void saveState();
        void restoreState();

        void clearUser();
    }

    @PerActivity
    public interface Presenter<V extends View, I extends Interactor> extends MvpPresenter<V, I> {
        void onLoginChaned(@NonNull String login);
        void onPasswordChanged(@NonNull String password);
        void onSignInButtonClicked();
        void onBackClick();
    }

    public interface View extends MvpView {
        void showError(@NotNull String msg);
        void showAuthError();
        void navigateToSearch();
        void renderProgress();
        void renderLogin(@NonNull String login);
        void renderPassword(@NonNull String password);
    }

}
