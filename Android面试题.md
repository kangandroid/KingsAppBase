
###1.是什么导致了ANR？
在Android中，程序的响应性是由Activity Manager与Window Manager系统服务来负责监控的。当系统监测到下面的条件之一时会显示ANR的对话框：

1. 对输入事件(例如硬件点击或者屏幕触摸事件)，5秒内都无响应。
2. BroadReceiver不能够在10秒内结束接收到任务。

对于你的应用中任何可能长时间执行的操作，你都不应该执行在UI线程。


###2.如何避免ANRs？
1. 对于你的应用中任何可能长时间执行的操作，你都不应该执行在UI线程。如网络请求，文件I/O，复杂的计算等。应放在工作线程中执行
2. 创建自己的线程或者HandlerThread稍微复杂一点。如果你想这样做，你应该通过Process.setThreadPriority()并传递THREAD_PRIORITY_BACKGROUND来设置线程的优先级为"background"。如果你不通过这个方式来给线程设置一个低的优先级，那么这个线程仍然会使得你的应用显得卡顿，因为这个线程默认与UI线程有着同样的优先级。
3. 不阻塞UI线程来等待工作线程的结果，通过UI线程创建Handler提供给工作线程来，来监听并响应工作线程。

4. BroadcastReceiver有特定执行时间的限制说明了broadcast receivers应该做的是：简短快速的任务，避免执行费时的操作，例如保存数据或者注册一个Notification。正如在UI线程中执行的方法一样，程序应该避免在broadcast receiver中执行费时的长任务。但不是采用通过工作线程来执行复杂的任务的方式，你的程序应该启动一个IntentService来响应intent broadcast的长时间任务。

###3.增加响应性
通常来说，100ms - 200ms是用户能够察觉到卡顿的上限。这样的话，下面有一些避免ANR的技巧：

* 如果你的程序需要响应正在后台加载的任务，在你的UI中可以显示ProgressBar来显示进度。
* 对游戏程序，在工作线程执行计算的任务。
* 如果你的程序在启动阶段有一个耗时的初始化操作，可以考虑显示一个闪屏，要么尽快的显示主界面，然后马上显示一个加载的对话框，异步加载数据。无论哪种情况，你都应该显示一个进度信息，以免用户感觉程序有卡顿的情况。
* 使用性能测试工具，例如Systrace与Traceview来判断程序中影响响应性的瓶颈。

###4.Android消息机制
Looper 构造函数私有 通过静态方法 prepare() 来为当前线程创建实例。 
线程唯一 保存在 ThreadLocal 用来保存线程私有的数据 getMap(curThread)得到ThreadLocalMap.getEntry(ThreadLocal) 的Looper
静态方法loop 先检查当前线程是否存在Looper 存在则for (;;)开启无线循环，从MessageQueue中取消息
 调用 msg.target.dispatchMessage(msg);处理消息 Message msg.target就是发送他的 handler

MessageQueue Looper在创建时就会在构造函数中创建 mQueue = new MessageQueue(quitAllowed)；
queue.enqueueMessage(msg, uptimeMillis) 对消息进行排序插入消息队列

MessageQueue中有几个native方法

    private native static long nativeInit();  在java的MessageQueue创建时C++同时也会创建消息队列
    private native static void nativeDestroy(long ptr);销毁消息队列
    private native void nativePollOnce(long ptr, int timeoutMillis); /*non-static for callbacks*/ epull机制唤醒C++层的looper 来读取消息
    private native static void nativeWake(long ptr);
    private native static boolean nativeIsPolling(long ptr);
    private native static void nativeSetFileDescriptorEvents(long ptr, int fd, int events);
    

Handler 负责发送消息（sendXXX Message 和post Runnable 最终被封装成Message 的.callBack）和并在创建的线程处理消息处理消息（onHandlerMessage）
所有发送消息最终调用 enqueueMessage --> msg.target = this
在调用 MessageQueue.enqueueMessage(msg, uptimeMillis)
消息处理 

    public void dispatchMessage(Message msg) {
        if (msg.callback != null) { // 
            handleCallback(msg);
        } else {
            if (mCallback != null) { // 创建handler时会创建 或者设置 
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg); // 最后才执行
        }
    }
###5. Android View绘制流程



###6. Android 屏幕渲染机制

###7. Android dalvik虚拟机和Art虚拟机的优化升级点


###8. Android 系统启动过程


###9. Activity 启动过程


###10. AMS

###11. WMS



###Glide 
1.Glide 对象是单例，用volatile修饰。创建过程用同步代码块。
    初始化过程：
    网络 
    缓存 
    生命周期
    
    target
    
    ActivityThread.java
    --------------------------------------------------------------
        public static void main(String[] args) {
        ...
            Looper.prepareMainLooper();
            ...
            ActivityThread thread = new ActivityThread();
            thread.attach(false, startSeq);
   
            if (sMainThreadHandler == null) {
                sMainThreadHandler = thread.getHandler();
            }
            ...
            Looper.loop();
            throw new RuntimeException("Main thread loop unexpectedly exited");
        }



