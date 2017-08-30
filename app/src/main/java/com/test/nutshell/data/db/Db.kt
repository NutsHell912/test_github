package com.test.nutshell.data.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.test.nutshell.util.Cursors
import com.test.nutshell.data.model.Repo as RepoModel
import com.test.nutshell.data.model.User as UserModel


object Db {
    fun insert(db: SQLiteDatabase, table: String, values: ContentValues): Long {
        val rowId = db.insert(table,
                null,
                values)

        if (rowId == -1L) {
            throw IllegalStateException("Не удалось сохранить $values.")
        }

        return rowId
    }

    fun insertOrReplace(db: SQLiteDatabase, table: String, values: ContentValues): Long {
        val rowId = db.insertWithOnConflict(table,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE)

        if (rowId == -1L) {
            throw IllegalStateException("Не удалось сохранить $values.")
        }

        return rowId
    }

    fun insertOrThrow(db: SQLiteDatabase, table: String, values: ContentValues): Long {
        val rowId = db.insertOrThrow(table, null, values)

        if (rowId == -1L) {
            throw IllegalStateException("Не удалось сохранить $values.")
        }

        return rowId
    }

    fun clearTable(db: SQLiteDatabase, table: String) {
        db.delete(table, null, null)
    }

    object User {
        const val TABLE_NAME = "user"

        const val COL_USER_ID = "user_id"
        const val COL_USER_NAME = "user_name"
        const val COL_USER_PASSWORD = "user_address"


        const val SQL_CREATE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "${COL_USER_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "${COL_USER_NAME} TEXT NULL, " +
                "${COL_USER_PASSWORD} TEXT NULL " +
                ");"

        val PROJECTION = arrayOf(COL_USER_ID,
                COL_USER_NAME,
                COL_USER_PASSWORD)

        fun UserModel.toContentValues(values: ContentValues? = null): ContentValues {
            val cv = values ?: ContentValues()
            cv.clear()

            cv.put(COL_USER_NAME, name)
            cv.put(COL_USER_PASSWORD, password)
            return cv
        }

        fun parseCursor(cursor: Cursor): UserModel {
            val userName = Cursors.getString(cursor, COL_USER_NAME)
            val userPassword = Cursors.getString(cursor, COL_USER_PASSWORD)

            return UserModel(userName,
                    userPassword
            )
        }
    }

    object Repo {
        const val TABLE_NAME = "photo"

        const val COL_ID = "id"
        const val COL_REPO_FULL_NAME = "full_name"
        const val COL_AVATART_URL = "photo_url"
        const val COL_DESCRIPTION = "post_titles"


        const val SQL_CREATE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "${COL_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "${COL_REPO_FULL_NAME} TEXT , " +
                "${COL_AVATART_URL} TEXT , " +
                "${COL_DESCRIPTION} TEXT " +
                ");"

        val PROJECTION = arrayOf(COL_REPO_FULL_NAME, COL_AVATART_URL,
                COL_DESCRIPTION)

        fun RepoModel.toContentValues(values: ContentValues? = null): ContentValues {
            val cv = values ?: ContentValues()
            cv.clear()
            cv.put(COL_REPO_FULL_NAME, fullName)
            cv.put(COL_AVATART_URL, url)
            cv.put(COL_DESCRIPTION, description)
            return cv
        }

        fun parseCursor(cursor: Cursor): RepoModel {
            val fullName = Cursors.getStringOrNull(cursor, COL_REPO_FULL_NAME)
            val avatarUrl = Cursors.getStringOrNull(cursor, COL_AVATART_URL)
            val description = Cursors.getStringOrNull(cursor, COL_DESCRIPTION)

            return RepoModel(
                    fullName = fullName,
                    description = description,
                    url = avatarUrl
            )
        }
    }


}