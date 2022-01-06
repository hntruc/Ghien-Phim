package com.example.ghienphim.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ghienphim.Cur
import java.util.*
import kotlin.collections.ArrayList

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // create table sql query
    private val TABLE_USER = Cur.name.toString()
    private val create_table = ("CREATE TABLE "+ TABLE_USER +" (" + COLUMN_FILM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FILM_NAME + " TEXT" +")")
    private val drop_user_table = "DROP TABLE IF EXISTS $TABLE_USER"
    private val create_if_not = ("CREATE TABLE IF NOT EXISTS $TABLE_USER" +" (" + COLUMN_FILM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FILM_NAME + " TEXT" +")")
    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(createusertable)
//        DATABASE_VERSION += 1

        db.execSQL(create_table)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //Drop User Table if exist
//        db.execSQL(dropusertable)
        db.execSQL(drop_user_table)
        // Create tables again
        onCreate(db)

    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    fun getAllFilm(): ArrayList<String> {
//        val filmList = kotlin.collections.ArrayList<String>()
        val columns = arrayOf(COLUMN_FILM_ID, COLUMN_FILM_NAME)
        val sortOrder = "$COLUMN_FILM_NAME ASC"
        val filmList = ArrayList<String>()
        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(TABLE_USER, columns, null, null, null, null,sortOrder)
        if (cursor.moveToFirst()) {
            do{
                val s = cursor.getString(cursor.getColumnIndex(COLUMN_FILM_NAME))
                filmList.add(s)
            } while(cursor.moveToNext())
//            for(i in 1..cursor.count) {
//                val film = cursor.getString(1)
//
//                filmList.add(film)
//                cursor.moveToNext()
//            }
        }
        cursor.close()
        db.close()
        return filmList
    }


    /**
     * This method is to create user record
     *
     * @param user
     */
    fun addFilm(fname: String) {
        val db = this.writableDatabase
//
//        val listU = db.query(TABLE_USER, //Table to query
//         arrayOf(COLUMN_USER_ID),        //columns to return
//        null,      //columns for the WHERE clause
//        null,  //The values for the WHERE clause
//        null,  //group the rows
//        null,   //filter by row groups
//        null)  //The sort order
//        val numU = listU.count()
        val values = ContentValues()
//        user.id = values
        values.put(COLUMN_FILM_NAME, fname)

        // Inserting Row
        db.insert(TABLE_USER,null, values)
//        val ins = db.insert(TABLE_USER, null, values)

        db.close()
//        return ins
    }

    /**
     * This method to update user record
     *
     * @param user
     */
//    fun updateUser(user: User) {
//        val db = this.writableDatabase
//
//        val values = ContentValues()
//        values.put(COLUMN_USER_NAME, user.name)
//        values.put(COLUMN_USER_EMAIL, user.email)
//        values.put(COLUMN_USER_PASSWORD, user.pass)
//        values.put(COLUMN_USER_AGE, user.age)
//
//        // updating row
//        db.update(
//            TABLE_USER, values, "$COLUMN_USER_ID = ?",
//            arrayOf(user.id.toString()))
//        db.close()
//    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
//    fun deleteFilm(fname: String) {
//
//        val db = this.writableDatabase
//        // delete user record by id
//        db.delete(
//            TABLE_USER, "$COLUMN_FILM_NAME = ?",
//            arrayOf(fname))
//        db.close()
//    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    fun checkFilmExist(fname: String): Boolean {
        // array of columns to fetch

//        val db = this.writableDatabase
//        db.execSQL("CREATE TABLE FAVORITE (COLUMN_FILM_NAME TEXT)")
//        db.close()
        val columns = arrayOf(COLUMN_FILM_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_FILM_NAME = ?"
        val selectionArgs = arrayOf(fname)
        //val columns = arrayOf(COLUMN_FILM_NAME)

        // selection criteria
//        val selection = "$COLUMN_FILM_NAME = ?"

        // selection argument
//        val selectionArgs = arrayOf(fname)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
//        val cursor = db.query(
//            TABLE_USER, //Table to query
//            columns,        //columns to return
//            selection,      //columns for the WHERE clause
//            selectionArgs,  //The values for the WHERE clause
//            null,  //group the rows
//            null,   //filter by row groups
//            null)  //The sort order


//        val cursorCount = cursor.count
//        cursor.close()
        db.execSQL(create_if_not)
        val cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
//        cursor.moveToNext()
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) { // co phim trong data
            return true
        }
        return false

    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
//    fun checkUser(username: String, password: String): Boolean {
//
//        // array of columns to fetch
//        val columns = arrayOf(COLUMN_USER_ID)
//
//        val db = this.readableDatabase
//
//        // selection criteria
//        val selection = "$COLUMN_USER_NAME = ? AND $COLUMN_USER_PASSWORD = ?"
//
//        // selection arguments
//        val selectionArgs = arrayOf(username, password)
//
//        // query user table with conditions
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        val cursor = db.query(
//            TABLE_USER, //Table to query
//            columns, //columns to return
//            selection, //columns for the WHERE clause
//            selectionArgs, //The values for the WHERE clause
//            null,  //group the rows
//            null, //filter by row groups
//            null) //The sort order
//
//        val cursorCount = cursor.count
//        cursor.close()
//        db.close()
//
//        if (cursorCount > 0)
//            return true
//        return false
//    }
//    fun checkWPassUser(username: String): Boolean {
//
//        // array of columns to fetch
//        val columns = arrayOf(COLUMN_USER_ID)
//
//        val db = this.readableDatabase
//
//        // selection criteria
//        val selection = "$COLUMN_USER_NAME = ?"
//
//        // selection arguments
//        val selectionArgs = arrayOf(username)
//
//        // query user table with conditions
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        val cursor = db.query(
//                TABLE_USER, //Table to query
//                columns, //columns to return
//                selection, //columns for the WHERE clause
//                selectionArgs, //The values for the WHERE clause
//                null,  //group the rows
//                null, //filter by row groups
//                null) //The sort order
//
//        val cursorCount = cursor.count
//        cursor.close()
//        db.close()
//
//        if (cursorCount > 0)
//            return true
//        return false
//    }

    companion object {

        // Database Version
        private var DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "FAVORITE.db"
//
//        // User table name
//        private var TABLE_USER = Cur.name.toString()
//
//        // User Table Columns names
        private val COLUMN_FILM_ID = "FilmID"
        private val COLUMN_FILM_NAME = "FilmName"

    }
}