package com.test.nutshell.data.model;


import android.support.annotation.NonNull;

public class User {
    @NonNull
    private final String name;
    @NonNull
    private final String password;

    public User(@NonNull String name, @NonNull String password) {
        this.name = name;
        this.password = password;
    }

    @NonNull
    public final String getName() {
        return this.name;
    }

    @NonNull
    public final String getPassword() {
        return this.password;
    }

}
