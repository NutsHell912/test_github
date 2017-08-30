package com.test.nutshell.util

import android.database.Cursor
import java.math.BigDecimal

object Cursors {
    fun getStringOrNull(cursor: Cursor, columnName: String): String? {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getString(cursor: Cursor, columnName: String): String {
        val value = getStringOrNull(cursor, columnName)
        return checkNotNull(value)
    }

    fun getBooleanOrNull(cursor: Cursor, columnName: String): Boolean? {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName)) != 0
    }

    fun getBoolean(cursor: Cursor, columnName: String): Boolean {
        val value = getBooleanOrNull(cursor, columnName)
        return checkNotNull(value)
    }

    fun getIntOrNull(cursor: Cursor, columnName: String): Int? {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName))
    }

    fun getInt(cursor: Cursor, columnName: String): Int {
        val value = getIntOrNull(cursor, columnName)
        return checkNotNull(value)
    }

    fun getDecimalOrNull(cursor: Cursor, columnName: String): BigDecimal? {
        return BigDecimal(cursor.getString(cursor.getColumnIndexOrThrow(columnName)))
    }

    fun getDecimal(cursor: Cursor, columnName: String): BigDecimal {
        val value = getDecimalOrNull(cursor, columnName)
        return checkNotNull(value)
    }

}