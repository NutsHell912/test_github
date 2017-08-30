package com.test.nutshell.data.db;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class SharedPreferncesHelper {
    private SharedPreferences preferences;
    @Inject
    SharedPreferncesHelper(@NotNull SharedPreferences preferences) {
        this.preferences = preferences;
    }
    @Nullable
    String getQuery() {
        return preferences.getString(QUERY, null);
    }
    void setQuery(@NotNull String query) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(QUERY, query);
        editor.commit();

    }
    private final static String QUERY = "QUERYY";
}
