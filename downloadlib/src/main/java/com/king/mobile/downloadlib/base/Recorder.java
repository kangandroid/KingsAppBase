package com.king.mobile.downloadlib.base;

import com.king.mobile.downloadlib.model.Task;
import com.king.mobile.downloadlib.model.ThreadTask;

// 记录员 负责 记录 和 查询 下载载状态
public abstract class Recorder {
    // 添加新下载任务
    public abstract void addTask(Task task);
    // 更新下载任务
    public abstract void updateTask(Task task);
    // 通过URL 获取下载任务
    public abstract Task getTaskByUrl(String url);
    // 通过ID 获取下载任务
    public abstract Task getTaskById(Long id);
    // 判断下载任务是否存在
    public abstract boolean idTaskExist(String url);

    public abstract void addThreadTask(ThreadTask threadTask);
}
