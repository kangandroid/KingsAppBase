package com.king.mobile.downloadlib

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TAB_THREAD_TASK")
data class ThreadTask(
        @PrimaryKey val id: Long,
        @ColumnInfo val url: String, // 地址
        @ColumnInfo val size: Long, // 任务段的长度
        @ColumnInfo val start: Long, // 任务的开始位置
        @ColumnInfo var fineshed: Long // 已完成的
)
