package com.king.player.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database( entities = {VideoInfo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VideoDao videoDao();
}
