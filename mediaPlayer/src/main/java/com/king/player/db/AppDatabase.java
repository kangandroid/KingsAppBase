package com.king.player.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.king.mobile.util.Loker;
import com.king.player.datasource.VideoCollectionDao;
import com.king.player.datasource.VideoDao;
import com.king.player.video.VideoCollection;
import com.king.player.video.VideoInfo;
import com.king.player.search.WebSiteDao;
import com.king.player.search.WebSiteInfo;

@Database(entities = {
        VideoInfo.class,
        WebSiteInfo.class,
        VideoCollection.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VideoDao videoDao();

    public abstract VideoCollectionDao videoCollectionDao();

    public abstract WebSiteDao webSiteDao();



    private static volatile AppDatabase INSTANCE;

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
            if(db instanceof AppDatabase ){
                AppDatabase appDatabase = (AppDatabase) db;
                WebSiteInfo info = new WebSiteInfo();
                info.isDefault = 1;
                info.useTimes=10000;
                info.title = "百度";
                info.url = "https://www.baidu.com";
                appDatabase.webSiteDao().insert(info);
            }
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
            Loker.d("AppDatabase---------onDestructiveMigration");
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "KingApp_DB")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}