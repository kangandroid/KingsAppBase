package com.king.mobile.downloadlib.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.king.mobile.downloadlib.model.Task;
import com.king.mobile.downloadlib.model.ThreadTask;

@Database(entities = {Task.class, ThreadTask.class}, version = 1, exportSchema = false)
public abstract class DownloadDB extends RoomDatabase {
    public abstract TaskDao taskDao();

    public abstract ThreadTaskDao threadTaskDao();

    private static volatile DownloadDB INSTANCE;

    public static DownloadDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DownloadDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DownloadDB.class, "DownloadDB_DB").build();
                }
            }
        }
        return INSTANCE;
    }
}
