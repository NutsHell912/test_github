package com.test.nutshell.di.component;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.test.nutshell.App;
import com.test.nutshell.data.db.Storage;
import com.test.nutshell.data.network.ApiServiceHelper;
import com.test.nutshell.di.module.ApplicationModule;
import com.test.nutshell.di.module.ServiceModule;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {ApplicationModule.class, ServiceModule.class}
)
public interface ApplicationComponent {
    void inject(@NotNull App var1);

    @NotNull
    Context context();

    @NotNull
    Application application();

    @NonNull
    ApiServiceHelper apiHelper();

    @NonNull
    Storage storage();
}
