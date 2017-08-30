package com.test.nutshell.di.module;

import android.app.Application;
import android.content.Context;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {
    @NotNull
    private final Application application;

    @Provides
    @NotNull
    public final Context provideContext() {
        return this.application;
    }

    @Provides
    @NotNull
    public final Application provideApplication() {
        return this.application;
    }

    @NotNull
    public final Application getApplication() {
        return this.application;
    }

    public ApplicationModule(@NotNull Application application) {
        this.application = application;
    }
}
