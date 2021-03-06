package com.king.mobile.wakap.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.king.mobile.util.Loker;


@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class WakapDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static volatile WakapDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Loker.d("AppDatabase---------onOpen");
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Loker.d("AppDatabase---------onCreate");
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
            Loker.d("AppDatabase---------onDestructiveMigration");
        }
    };

    public static WakapDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WakapDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, WakapDatabase.class, "Wakap_DB")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}