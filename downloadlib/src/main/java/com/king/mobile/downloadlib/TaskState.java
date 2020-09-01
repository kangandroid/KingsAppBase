package com.king.mobile.downloadlib;

public class TaskState {
    public static final int STATE_URL_ERROR = -1;// 地址有误
    public static final int STATE_CREATED = 0;//初始状态
    public static final int STATE_WAITING = 1;// 任务分配完成，地址无误可以下载
    public static final int STATE_DOWNLOADING = 2;// 下载中
    public static final int STATE_PAUSE = 3;// 暂停中
    public static final int STATE_DOWNLOAD_ERROR = 4;// 下载出错
    public static final int STATE_FINISHED = 100;// 已完成
}
