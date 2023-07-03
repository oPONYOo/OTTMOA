package com.ponyo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DB::class],
    version = 1,
    exportSchema = false,
)

abstract class InfoDatabase : RoomDatabase() {


    abstract fun getDao(): InfoDao

    companion object {
        private var dbInstance: InfoDatabase? = null

        fun getAppDB(context: Context): InfoDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext, InfoDatabase::class.java, "INFO"
                )
                    .allowMainThreadQueries()
                    .build()

            }
            return dbInstance!!
        }


    }


}