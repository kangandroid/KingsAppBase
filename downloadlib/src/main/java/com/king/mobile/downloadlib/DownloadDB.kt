package com.king.mobile.downloadlib

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Task::class, ThreadTask::class], version = 1, exportSchema = false)
abstract class DownloadDB : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun threadTaskDao(): ThreadTaskDao

    companion object {
        private var instance: DownloadDB? = null
        @Synchronized
        fun getDatabase(context: Context): DownloadDB {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,DownloadDB::class.java, "Download_DB")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                initData(context.applicationContext)
                            }
                        })
                        .build()
            }
            return instance as DownloadDB
        }
        private fun initData(context: Context) {
            // inserts in Room are executed on the current thread, so we insert in the background
            ioThread {

            }

        }
    }
}