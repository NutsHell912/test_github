package com.test.nutshell.data.db

import android.content.ContentValues
import android.content.SharedPreferences
import com.google.gson.Gson
import com.test.nutshell.data.db.Db.Repo.toContentValues
import com.test.nutshell.data.db.Db.User.toContentValues
import com.test.nutshell.data.model.Repo
import com.test.nutshell.data.model.User
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Storage
@Inject
constructor(private val dbOpenHelper: DbOpenHelper, private val gson: Gson, private val preferences: SharedPreferncesHelper) {

    fun getUser(): User? {
        val db = dbOpenHelper.readableDatabase

        db.query(Db.User.TABLE_NAME, Db.User.PROJECTION,
                null, null, null, null,
                Db.User.COL_USER_ID
        ).use { cursor ->
            val users = ArrayList<User>(cursor.count)
            if (cursor.count == 1) {
                cursor.moveToNext()
                return Db.User.parseCursor(cursor)
            }
            return null
        }
    }

    fun putUser(user: User?) {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
            db.delete(Db.User.TABLE_NAME, null, null)
            user?.let {
                val cv = ContentValues()
                user.toContentValues(cv)
                Db.insertOrReplace(db, Db.User.TABLE_NAME, cv)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun getRepos(): List<Repo> {
        val db = dbOpenHelper.readableDatabase

        db.query(Db.Repo.TABLE_NAME, Db.Repo.PROJECTION,
                null, null, null, null,
                Db.Repo.COL_ID
        ).use { cursor ->
            val photos = ArrayList<Repo>(cursor.count)

            while (cursor.moveToNext()) {
                val photo = Db.Repo.parseCursor(cursor)
                photos.add(photo)
            }

            return photos
        }
    }

    fun putRepos(repos: List<Repo>) {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
           // db.delete(Db.Repo.TABLE_NAME, null, null)

            val cv = ContentValues()

            for (photo in repos) {
                photo.toContentValues(cv)
                Db.insert(db, Db.Repo.TABLE_NAME, cv)
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }
    fun clearRepos() {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
            db.delete(Db.Repo.TABLE_NAME, null, null)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun getQuery():String? {
        return preferences.query
    }
    fun putQuery(query: String) {
        preferences.setQuery(query)
    }


}
