package com.king.mobile.downloadlib;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

abstract class DownloadDB extends RoomDatabase {
    abstract TaskDao taskDao();

    abstract ThreadTaskDao threadTaskDao();

    private static volatile DownloadDB INSTANCE;

    public static DownloadDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DownloadDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DownloadDB.class, "KingApp_DB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
