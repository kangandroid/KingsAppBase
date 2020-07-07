package com.king.mobile.downloadlib;

interface DownloadListener {
    void onError(Task task);

    void onProgress(Task task);

    void onStart(Task task);

    void onFinished(Task task);

    void onStop(Task task);
}