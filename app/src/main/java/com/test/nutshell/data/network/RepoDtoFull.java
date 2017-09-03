package com.test.nutshell.data.network;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class RepoDtoFull {
    @SerializedName("incomplete_results")
    private boolean results;
    @SerializedName("items")
    @NonNull
    private List<RepoDto> items;

    public final boolean getResults() {
        return this.results;
    }

    public RepoDtoFull(boolean results, @NonNull List<RepoDto> items) {
        this.results = results;
        this.items = items;
    }

    public boolean isResults() {
        return results;
    }

    @NonNull
    public List<RepoDto> getItems() {
        return items;
    }

    public class RepoDto {
        @SerializedName("full_name")
        @Nullable
        private String fullName;
        @SerializedName("description")
        @Nullable
        private String description;
        @SerializedName("owner")
        @Nullable
        private UserDto owner;

        public RepoDto(@Nullable String fullName, @Nullable String description, @Nullable UserDto owner) {
            this.fullName = fullName;
            this.description = description;
            this.owner = owner;
        }

        @Nullable
        public String getFullName() {
            return fullName;
        }

        @Nullable
        public String getDescription() {
            return description;
        }

        @Nullable
        public UserDto getOwner() {
            return owner;
        }
    }

    public class UserDto {
        @SerializedName("avatar_url")
        @Nullable
        private final String avatarUrl;

        public UserDto(@Nullable String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @Nullable
        public String getAvatarUrl() {
            return avatarUrl;
        }
    }
}