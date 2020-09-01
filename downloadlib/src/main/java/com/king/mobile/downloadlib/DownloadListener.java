package com.king.mobile.downloadlib;

import com.king.mobile.downloadlib.model.Task;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public interface DownloadListener {
    @Subscribe(sticky = false,threadMode = ThreadMode.MAIN)
    void onError(Task task);

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    void onProgress(Task task);

    @Subscribe(sticky = false,threadMode = ThreadMode.MAIN)
    void onStart(Task task);

    @Subscribe(sticky = false,threadMode = ThreadMode.MAIN)
    void onFinished(Task task);

    @Subscribe(sticky = false,threadMode = ThreadMode.MAIN)
    void onStop(Task task);
}