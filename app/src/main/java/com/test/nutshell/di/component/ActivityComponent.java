package com.test.nutshell.di.component;

import android.support.annotation.NonNull;

import com.test.nutshell.di.PerActivity;
import com.test.nutshell.di.module.ActivityModule;
import com.test.nutshell.search.SearchActivity;
import com.test.nutshell.start.StartActivity;


import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class}
)
public interface ActivityComponent {
    void inject(@NonNull StartActivity activity);

    void inject(@NonNull SearchActivity activity);
}
