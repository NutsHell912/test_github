package com.test.nutshell;

import android.app.Application;

import com.test.nutshell.di.component.ApplicationComponent;
import com.test.nutshell.di.component.DaggerApplicationComponent;
import com.test.nutshell.di.module.ApplicationModule;
import com.test.nutshell.di.module.ServiceModule;


public class App extends Application {
    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .serviceModule(new ServiceModule())
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);


    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
