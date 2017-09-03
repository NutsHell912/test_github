package com.test.nutshell.di.module;


import android.support.annotation.NonNull;

import com.test.nutshell.di.PerActivity;
import com.test.nutshell.search.SearchInteractor;
import com.test.nutshell.search.SearchMVP;
import com.test.nutshell.search.SearchPresenter;
import com.test.nutshell.start.StartInteractor;
import com.test.nutshell.start.StartMVP;
import com.test.nutshell.start.StartPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {
    @Provides
    @PerActivity
    @NonNull
    public final StartMVP.Presenter<StartMVP.View, StartMVP.Interactor> provideStartPresenter(
            @NonNull StartPresenter<StartMVP.View, StartMVP.Interactor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    @NonNull
    public final StartMVP.Interactor provideStartMvpInteractor(@NonNull StartInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerActivity
    @NonNull
    public final SearchMVP.Presenter<SearchMVP.View, SearchMVP.Interactor> providePhotoPresenter(
            @NonNull SearchPresenter<SearchMVP.View, SearchMVP.Interactor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    @NonNull
    public final SearchMVP.Interactor providePhotoMvpInteractor(@NonNull SearchInteractor interactor) {
        return interactor;
    }
}
