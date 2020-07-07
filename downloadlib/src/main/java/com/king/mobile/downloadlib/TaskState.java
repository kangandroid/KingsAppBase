package com.king.mobile.downloadlib;

enum TaskState {

    STATE_CREATE("初始状态", 0), // 初始状态
    STATE_ERROR("下载出错", -1),  // 地址有误
    STATE_START("开始下载", 1),  // 任务分配完成，地址无误可以下载
    STATE_DOWNLOADING("下载中", 2), // 下载中
    STATE_STOP("暂停下载", 3),// 暂停中
    STATE_FINISHED("下载完成", 4);// 已完成

    public int code;
    public String desc;

    private TaskState(String desc, int code) {
        this.code = code;
        this.desc = desc;
    }

    ;
}
