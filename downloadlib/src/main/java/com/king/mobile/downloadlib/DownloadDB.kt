package com.king.mobile.downloadlib

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class DownloadDB : RoomDatabase() {


    abstract fun taskDao(): TaskDao

    companion object {
        lateinit var INSTANCE: DownloadDB
        open fun getDatabase(context: Context): DownloadDB? {
            if (INSTANCE == null) {
                synchronized(DownloadDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                DownloadDB::class.java, "Download_DB")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }


}