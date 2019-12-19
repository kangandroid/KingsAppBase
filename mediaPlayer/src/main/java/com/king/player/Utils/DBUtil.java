package com.king.player.Utils;

import androidx.room.Room;

import com.king.player.App;
import com.king.player.model.AppDatabase;

public class DBUtil {
    public static AppDatabase getDB() {
        AppDatabase db = Room.databaseBuilder(App.getContext(), AppDatabase.class, "DB-KingApp").build();
        return db;
    }
}
