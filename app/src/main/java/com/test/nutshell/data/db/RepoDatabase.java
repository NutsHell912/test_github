package com.test.nutshell.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.test.nutshell.data.model.Repo;

@Database(entities = {Repo.class}, version = 1)
public abstract class RepoDatabase extends RoomDatabase {
    public abstract RepoDao repoDao();

    public static final String DATABASE_NAME = "RepoDatabase";
    public static final String TABLE_NAME = "repos";

}
