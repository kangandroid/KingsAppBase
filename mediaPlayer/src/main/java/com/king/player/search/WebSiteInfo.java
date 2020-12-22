package com.king.player.search;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "WEB_SITE")
public class WebSiteInfo {
    @Ignore
    private static final long serialVersionUID = 8822818790694831649L;
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo
    public String title;
    @ColumnInfo
    public int useTimes;
    @ColumnInfo
    public String url;
    @ColumnInfo(defaultValue = "0")
    public int isDefault;     //  1 为默认地址 ， 0

}
