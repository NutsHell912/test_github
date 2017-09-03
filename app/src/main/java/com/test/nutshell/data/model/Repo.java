package com.test.nutshell.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.test.nutshell.data.db.RepoDatabase;
import com.test.nutshell.data.network.RepoDtoFull;


@Entity(tableName = RepoDatabase.TABLE_NAME)
public class Repo {

    @PrimaryKey(autoGenerate = true)
    private Integer uid;

    @ColumnInfo(name = "full_name")
    @Nullable
    private  String fullName;
    @Nullable
    private  String description;
    @Nullable
    private  String url;

    @Nullable
    public final String getFullName() {
        return this.fullName;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setFullName(@Nullable String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    public Repo(@Nullable String fullName, @Nullable String description, @Nullable String url) {
        this.fullName = fullName;
        this.description = description;
        this.url = url;
    }

    public Repo(@NonNull RepoDtoFull.RepoDto repoDto) {
        fullName = repoDto.getFullName();
        description = repoDto.getDescription();
        if(repoDto.getOwner() != null) {
            url = repoDto.getOwner().getAvatarUrl();
        } else {
            url = null;
        }
    }
}
