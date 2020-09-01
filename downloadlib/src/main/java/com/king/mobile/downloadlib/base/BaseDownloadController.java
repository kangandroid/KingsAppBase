package com.king.mobile.downloadlib.base;

import com.king.mobile.downloadlib.model.Task;

public interface BaseDownloadController {

    /**
     * 开始下载任务
     */
    void start(Task task);

    /**
     * 暂停下载任务
     */
    void pause(Task task);

    /**
     * 删除下载任务
     */
    void delete(Task task);

}
