package com.test.nutshell.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.test.nutshell.data.model.Repo;

import java.util.List;

@Dao
public interface RepoDao {
    @Query("SELECT * FROM " + RepoDatabase.TABLE_NAME)
    List<Repo> getAll();

    @Insert
    void insertAll(Repo... repos);

    @Query("DELETE FROM " + RepoDatabase.TABLE_NAME)
    void clearTable();
}
