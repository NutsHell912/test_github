package com.test.nutshell.di.module;


import android.content.Context;
import android.content.SharedPreferences;

import com.test.nutshell.di.PerActivity;
import com.test.nutshell.search.SearchInteractor;
import com.test.nutshell.search.SearchMVP;
import com.test.nutshell.search.SearchPresenter;
import com.test.nutshell.start.StartInteractor;
import com.test.nutshell.start.StartMVP;
import com.test.nutshell.start.StartPresenter;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {
    @Provides
    @PerActivity
    @NotNull
    public final StartMVP.Presenter<StartMVP.View,StartMVP.Interactor> provideStartPresenter(
            @NotNull StartPresenter<StartMVP.View,StartMVP.Interactor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    @NotNull
    public final StartMVP.Interactor provideStartMvpInteractor(@NotNull StartInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerActivity
    @NotNull
    public final SearchMVP.Presenter<SearchMVP.View,SearchMVP.Interactor> providePhotoPresenter(
            @NotNull SearchPresenter<SearchMVP.View,SearchMVP.Interactor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    @NotNull
    public final SearchMVP.Interactor providePhotoMvpInteractor(@NotNull SearchInteractor interactor) {
        return interactor;
    }
}
