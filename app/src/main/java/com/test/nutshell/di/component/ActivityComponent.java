package com.test.nutshell.di.component;

import com.test.nutshell.di.PerActivity;
import com.test.nutshell.di.module.ActivityModule;
import com.test.nutshell.search.SearchActivity;
import com.test.nutshell.start.StartActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class}
)
public interface ActivityComponent {
    void inject(@NotNull StartActivity activity);

    void inject(@NotNull SearchActivity activity);
}
