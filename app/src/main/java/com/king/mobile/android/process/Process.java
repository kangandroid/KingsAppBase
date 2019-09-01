package com.king.mobile.android.process;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Process {
    public String id;
    @PrimaryKey
    public int uid;
    @ColumnInfo(name ="first_name")
    public String firstName;
}
