package com.test.nutshell.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.nutshell.App;
import com.test.nutshell.di.component.ActivityComponent;
import com.test.nutshell.di.component.DaggerActivityComponent;
import com.test.nutshell.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity
        implements MvpView {
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void setActivityComponent(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
    }

}