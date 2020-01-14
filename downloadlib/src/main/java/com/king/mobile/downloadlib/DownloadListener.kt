package com.king.mobile.downloadlib

interface DownloadListener {
    fun onError(task: Task)
    fun onProgress(task: Task)
    fun onStart(task: Task)
    fun onFineshed(task: Task)
    fun onStop(task: Task)
}