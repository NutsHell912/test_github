package com.test.nutshell.base;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;



public class BasePresenter<V extends MvpView, I extends MvpInteractor> implements MvpPresenter<V, I> {
    private V mvpView;
    private I mvpInteractor;

    public void onAttach(@NonNull V mvpView) {
        this.mvpView = mvpView;
    }

    public void onDetach() {
        this.mvpView = null;
    }

    @Nullable
    public V getMvpView() {
        return this.mvpView;
    }

    @NonNull
    public I getInteractor() {
        return this.mvpInteractor;
    }

    @Inject
    public BasePresenter(@NonNull I mvpInteractor) {
        this.mvpInteractor = mvpInteractor;
    }
}
