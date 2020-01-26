package com.king.mobile.downloadlib


class TaskState {
    companion object {
        val STATE_CREATE = 0  // 初始状态
        val STATE_ERROR = -1  // 地址有误
        val STATE_START = 1  // 地址无误可以下载
        val STATE_DOWNLOADING = 2 // 下载中
        val STATE_STOP = 3 // 暂停中
        val STATE_FINISHED = 4 // 已完成
    }
}