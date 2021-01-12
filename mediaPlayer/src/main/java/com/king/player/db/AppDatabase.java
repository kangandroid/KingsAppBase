package com.king.player.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

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
        VideoCollection.class}, version = 2, exportSchema = false)
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
            super.onCreate(db); //默认创建数据库 还可以做额外的事
            // 创建数据库

        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
            int version = db.getVersion();
            // 修改已有的库 （升级）

            Loker.d("AppDatabase---------onDestructiveMigration ");
        }


    };

    public static AppDatabase getDatabase(final Context context) {
        MigrationContainer mc = new MigrationContainer();
        Migration mig_1_2 = new Migration(1, 2){
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                String sql = "";
                database.execSQL(sql);
            }
        };
        mc.addMigrations();
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "KingApp_DB")
                            .addMigrations(mig_1_2)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}