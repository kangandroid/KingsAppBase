package com.king.mobile.testapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

class SQLiteHelper extends SQLiteOpenHelper {
    private static final String NAME = "TestSQLite";
    public static final int VERSION = 3;

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建User表。V == 1
//        String sql = "CREATE TABLE USER(" +
//                "ID INT PRIMARY KEY         NOT NULL," +
//                "NAME           TEXT(30)    NOT NULL," +
//                "AGE            INT         NOT NULL,"
//                ");";

//        创建User表。V == 2
//        String sql = "CREATE TABLE USER(" +
//                "ID INT PRIMARY KEY         NOT NULL," +
//                "NAME           TEXT(30)    NOT NULL," +
//                "AGE            INT         NOT NULL,"
//                ");";
//      创建User表  V==3。
        String sql = "CREATE TABLE USER(" +
                "ID INT PRIMARY KEY         NOT NULL," +
                "NAME           TEXT(30)    NOT NULL," +
                "AGE            INT         NOT NULL," +
                "NICK_NAME      CHAR(40)," +
                "ADDRESS        CHAR(50) NOT NULL" +
                ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = null;
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE USER ADD COLUMN (" +
                        "NICK_NAME CHAR(40) NOT NULL ," +
                        "ADDRESS CHAR(50) DEFAULT '北京'" +
                        ");";
                sql+="";
                break;
            case 2:
                sql = "ALTER TABLE USER ADD NICK_NAME CHAR(40);";
                break;
        }
        db.execSQL(sql);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        String sql = null;
        switch (oldVersion) {
            case 3:
                sql = "ALTER TABLE USER  ADD(" +
                        "NICK_NAME CHAR(40) NOT NULL ," +
                        "ADDRESS CHAR(50) DEFAULT '北京'" +
                        ");";
                break;
            case 2:
                sql = "ALTER TABLE USER ADD COLUMN NICK_NAME CHAR(40);";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + oldVersion);
        }
        db.execSQL(sql);

    }
}
