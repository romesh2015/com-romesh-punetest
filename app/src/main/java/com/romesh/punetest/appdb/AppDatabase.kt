package com.romesh.punetest.appdb
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.romesh.punetest.profile.model.ApiResponse
/*
 This class used to store data in database to show user profiles offline
 */
class AppDatabase (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "AppDatabase"
        private val TABLE_PROFILE = "ProfileTable"
        private val KEY_ID = "_id"
        private val KEY_GENDER = "gender"
        private val KEY_NAME = "name"
        private val KEY_STREET = "street"
        private val KEY_CITY = "city"
        private val KEY_STATE = "state"
        private val KEY_ZIP = "zip"
        private val KEY_EMAIL = "email"
        private val KEY_USERNAME = "username"
        private val KEY_SALT = "salt"
        private val KEY_REGISTER = "registered"
        private val KEY_PHONE = "phone"
        private val KEY_CELL = "cell"
        private val KEY_SSN = "SSN"
        private val KEY_PICTURE = "picture"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_PROFILE_TABLE = ("CREATE TABLE " + TABLE_PROFILE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_GENDER + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_STREET + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_STATE + " TEXT,"
                + KEY_ZIP + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_REGISTER + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_CELL + " TEXT,"
                + KEY_SSN + " TEXT,"
                + KEY_PICTURE + " TEXT" + ")")
        db?.execSQL(CREATE_PROFILE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE)
        onCreate(db)
    }
    //method to insert data
    fun addProfileFavourite(apiResponse: ApiResponse):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var profileData=apiResponse.results[0].user
        contentValues.put(KEY_GENDER, profileData.gender)
        contentValues.put(KEY_NAME,profileData.name.title+","+profileData.name.first+","+profileData.name.last)
        contentValues.put(KEY_STREET, profileData.location.street)
        contentValues.put(KEY_CITY, profileData.location.city)
        contentValues.put(KEY_STATE, profileData.location.state)
        contentValues.put(KEY_ZIP, profileData.location.zip)
        contentValues.put(KEY_EMAIL, profileData.email)
        contentValues.put(KEY_USERNAME, profileData.username)
        contentValues.put(KEY_REGISTER, profileData.registered)
        contentValues.put(KEY_PHONE, profileData.phone)
        contentValues.put(KEY_CELL, profileData.cell)
        contentValues.put(KEY_SSN, profileData.SSN)
        contentValues.put(KEY_PICTURE, profileData.picture)
        // Inserting Row
        val success = db.insert(TABLE_PROFILE, null, contentValues)
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewProfile(): ArrayList<ProfileModel> {
        val profileList:ArrayList<ProfileModel> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_PROFILE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            do {
                var modelProfile=ProfileModel()
                modelProfile.gender=cursor.getString(cursor.getColumnIndex(KEY_GENDER))
                modelProfile.name=cursor.getString(cursor.getColumnIndex(KEY_NAME))
                modelProfile.street=cursor.getString(cursor.getColumnIndex(KEY_STREET))
                modelProfile.city=cursor.getString(cursor.getColumnIndex(KEY_CITY))
                modelProfile.state=cursor.getString(cursor.getColumnIndex(KEY_STATE))
                modelProfile.zip=cursor.getString(cursor.getColumnIndex(KEY_ZIP))
                modelProfile.email=cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                modelProfile.username=cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
                modelProfile.register=cursor.getString(cursor.getColumnIndex(KEY_REGISTER))
                modelProfile.phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                modelProfile.cell=cursor.getString(cursor.getColumnIndex(KEY_CELL))
                modelProfile.ssn=cursor.getString(cursor.getColumnIndex(KEY_SSN))
                modelProfile.picture=cursor.getString(cursor.getColumnIndex(KEY_PICTURE))
                profileList.add(modelProfile)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return profileList
    }

}