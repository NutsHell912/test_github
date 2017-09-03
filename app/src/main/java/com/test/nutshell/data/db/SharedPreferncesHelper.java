package com.test.nutshell.data.db;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.test.nutshell.data.model.User;


import javax.inject.Inject;

public class SharedPreferncesHelper {
    private SharedPreferences preferences;

    @Inject
    SharedPreferncesHelper(@NonNull SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Nullable
    String getQuery() {
        return preferences.getString(QUERY, null);
    }

    void setQuery(@NonNull String query) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(QUERY, query);
        editor.commit();

    }

    @NonNull
    User getUser() {
        String username = preferences.getString(USERNAME, "");
        String password = preferences.getString(PASSWORD, "");
        return new User(username, password);

    }

    void putUser(@NonNull User user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, user.getName());
        editor.putString(PASSWORD, user.getPassword());
        editor.commit();

    }

    private final static String QUERY = "QUERYY";
    private final static String USERNAME = "login";
    private final static String PASSWORD = "PASSWORD";

}
