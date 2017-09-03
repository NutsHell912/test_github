package com.test.nutshell.start;

import android.support.annotation.NonNull;

import com.test.nutshell.base.BaseInteractor;
import com.test.nutshell.data.db.Storage;
import com.test.nutshell.data.model.User;
import com.test.nutshell.data.network.ApiServiceHelper;


import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class StartInteractor extends BaseInteractor implements StartMVP.Interactor {
    private ApiServiceHelper apiServiceHelper;
    private Storage storage;
    @NonNull
    private String login = "";
    @NonNull
    private String password = "";

    @Inject
    public StartInteractor(@NonNull ApiServiceHelper apiServiceHelper, @NonNull Storage storage) {
        super();
        this.apiServiceHelper = apiServiceHelper;
        this.storage = storage;
    }

    @Override
    public void onLoginChaned(@NonNull String login) {
        this.login = login;
    }

    @Override
    public void onPasswordChanged(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String getLogin() {
        return login;
    }

    @NonNull
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void saveState() {
        storage.putUser(new User(login, password));
    }

    @Override
    public void restoreState() {
        User user = storage.getUser();
        if(user != null) {
            login = user.getName();
            password = user.getPassword();
        }
    }

    @Override
    public void clearUser() {
        storage.putUser(null);
    }

    @Override
    public Completable signIn() {
        storage.putUser(new User(login, password));
        return apiServiceHelper.signIn()
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        storage.putUser(new User(login, password));
                    }
                });
    }
}
