package com.test.nutshell.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;



public class BasePresenter<V extends MvpView, I extends MvpInteractor> implements MvpPresenter<V, I> {
    private V mvpView;
    private I mvpInteractor;

    public void onAttach(@NotNull V mvpView) {
        this.mvpView = mvpView;
    }

    public void onDetach() {
        this.mvpView = null;
    }

    @Nullable
    public V getMvpView() {
        return this.mvpView;
    }

    @NotNull
    public I getInteractor() {
        return this.mvpInteractor;
    }

    @Inject
    public BasePresenter(@NotNull I mvpInteractor) {
        this.mvpInteractor = mvpInteractor;
    }
}
