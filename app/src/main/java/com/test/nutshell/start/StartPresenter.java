package com.test.nutshell.start;

import com.test.nutshell.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static com.test.nutshell.R.id.login;

public class StartPresenter<V extends StartMVP.View, I extends StartMVP.Interactor>
        extends BasePresenter<V, I> implements StartMVP.Presenter<V, I> {
    private Disposable userDisposable;

    @Inject
    public StartPresenter(@NotNull I mvpInteractor) {
        super(mvpInteractor);
        this.userDisposable = Disposables.disposed();
    }

    @Override
    public void onAttach(@NotNull V mvpView) {
        super.onAttach(mvpView);
        getInteractor().restoreState();
        render();
    }

    @Override
    public void onDetach() {
        userDisposable.dispose();
        getInteractor().saveState();
        super.onDetach();
    }

    @Override
    public void onLoginChaned(@android.support.annotation.NonNull @NonNull String login) {
        getInteractor().onLoginChaned(login);
    }

    @Override
    public void onPasswordChanged(@android.support.annotation.NonNull @NonNull String password) {
        getInteractor().onPasswordChanged(password);
    }

    @Override
    public void onSignInButtonClicked() {
        getInteractor().signIn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (getMvpView() != null) {
                            getMvpView().navigateToSearch();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (getMvpView() != null) {
                            if (throwable instanceof HttpException && ((HttpException) throwable).code() == 401) {
                                getMvpView().showAuthError();
                            } else {
                                getMvpView().showError(throwable.getMessage());
                            }
                        }
                    }
                });
        getMvpView().renderProgress();

    }

    private void render() {
        if (getMvpView() != null) {
            getMvpView().renderLogin(getInteractor().getLogin());
            getMvpView().renderPassword(getInteractor().getPassword());
        }
    }

    @Override
    public void onBackClick() {
        if (getMvpView() != null) {
            getInteractor().clearUser();
            getMvpView().navigateToSearch();
        }
    }
}
