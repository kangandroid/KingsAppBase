package com.king.mobile.downloadlib

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TAB_TASK")
data class Task(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @PrimaryKey val url: String,
        @ColumnInfo var name: String?,
        @ColumnInfo var path: String?,
        @ColumnInfo var size: Long,
        @ColumnInfo val md5: String?,
        @ColumnInfo var progress: Float?,
        @ColumnInfo var completed: Long?,
        @ColumnInfo var state: Int,
        @ColumnInfo var type: String?,
        @ColumnInfo var priority: Int, // 默认为0，最小
        @ColumnInfo val createdAt: Long

)