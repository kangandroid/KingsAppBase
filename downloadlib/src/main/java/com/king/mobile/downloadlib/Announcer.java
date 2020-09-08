package com.king.mobile.downloadlib;

import org.greenrobot.eventbus.EventBus;

// 广播员 负责发送下载状态变更通知给订阅者
public class Announcer {
//    public static final String TYPE_CREATE = "";
//    public static final String TYPE_PAUSE = "";
//    public static final String TYPE_DOWNLOADING = "type_downloading";
//    public static final String TYPE_COMPLETE = "";
//    public static final String TYPE_WAITING = "";
//    public static final String TYPE_ERROR = "";

    public static void register(Object listener){
        EventBus.getDefault().register(listener);
    }

    public static void unregister(Object listener) {
        EventBus.getDefault().unregister(listener);
    }

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    public static void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    public static void removeSticky(Class cls) {
        EventBus.getDefault().removeStickyEvent(cls);
    }
    public static void removeSticky(Object event) {
        EventBus.getDefault().removeStickyEvent(event);
    }

    public static void removeAllStickyEvents() {
        EventBus.getDefault().removeAllStickyEvents();
    }

}
