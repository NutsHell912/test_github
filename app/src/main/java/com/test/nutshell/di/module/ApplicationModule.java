package com.test.nutshell.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;


import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {
    @NonNull
    private final Application application;

    @Provides
    @NonNull
    public final Context provideContext() {
        return this.application;
    }

    @Provides
    @NonNull
    public final Application provideApplication() {
        return this.application;
    }

    @NonNull
    public final Application getApplication() {
        return this.application;
    }

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }
}
