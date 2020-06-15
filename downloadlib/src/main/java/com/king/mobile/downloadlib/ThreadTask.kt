package com.king.mobile.downloadlib

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 任务下载中的进度和状态
 */
@Entity(tableName = "TAB_THREAD_TASK")
data class ThreadTask(
        @PrimaryKey val id: Long,
        @ColumnInfo val url: String, // 地址
        @ColumnInfo val size: Long, // 任务段的长度
        @ColumnInfo(defaultValue = "0") var start: Long, // 任务的开始位置
        @ColumnInfo(defaultValue = "0") var finished: Long, // 已完成的进度
        @ColumnInfo(defaultValue = "false") var isFinished: Boolean, // 是否完成
        @ColumnInfo val createdAt: Long // 创建时间
)
