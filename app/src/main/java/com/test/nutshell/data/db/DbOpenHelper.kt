package com.test.nutshell.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbOpenHelper
@Inject
constructor(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Db.User.SQL_CREATE)
        db.execSQL(Db.Repo.SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Db.User.TABLE_NAME};")
        db.execSQL("DROP TABLE IF EXISTS ${Db.Repo.TABLE_NAME};")
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "test.db"
        private const val DB_VERSION = 5
    }
}