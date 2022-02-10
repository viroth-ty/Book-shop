package org.viroth.bookstore.app.service

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import org.viroth.bookstore.app.model.HydraMember

class SQLiteDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "book_database"
        const val TABLE_FAVOURITE = "favourite_table"

        /**
         *column news
         */
        const val KEY_ID = "id"
        const val KEY_BOOK_ID = "book_id"
        const val KEY_TITLE = "title"
        const val KEY_SAVE = "isSave"

    }

    override fun onCreate(database: SQLiteDatabase) {
        val createFavouriteTable = ("CREATE TABLE " + TABLE_FAVOURITE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_BOOK_ID + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_SAVE + " INTEGER DEFAULT 1" + ")")


        database.execSQL(createFavouriteTable)
    }

    override fun onUpgrade(database: SQLiteDatabase, p1: Int, p2: Int) {

        with(database) {
            execSQL("DROP TABLE IF EXISTS $TABLE_FAVOURITE")

            onCreate(this)
        }
    }

    fun addFavouriteNews(
        id: String,
        title: String,
    ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_BOOK_ID, id)
        contentValues.put(KEY_TITLE, title)
        contentValues.put(KEY_SAVE, 1)
        return db.insert(TABLE_FAVOURITE, null, contentValues)
    }

    fun removeFavouriteNews(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_FAVOURITE, "$KEY_BOOK_ID = $id", null)
    }


    @SuppressLint("Recycle", "Range")
    fun getFavouriteBook(): ArrayList<HydraMember> {
        val empList: ArrayList<HydraMember> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_FAVOURITE"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var isSave: Int
        var bookID: String
        var title: String

        if (cursor.moveToFirst()) {
            do {
                bookID = cursor.getString(cursor.getColumnIndex(KEY_BOOK_ID))
                title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                isSave = cursor.getInt(cursor.getColumnIndex(KEY_SAVE))
                val emp = HydraMember(id = bookID, title = title, isSave = isSave)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
}