## Android系统启动过程
	--> 电源 
	--> BootLoader 引导程序
	--> 启动Linux内核
	--> init进程
	---->挂载启动所需的文件目录
	---->初始化属性服务
	--> 创建zygote进程
	--> 创建SystemServer进程
	--> 创建ActivityManangerService
	--> 启动Launcher进程




## Context 解析

>Context 父类
>>ContextImp 具体实现
>>
>>ContextWrapper 包装类 attach 了ContextImp对象
>>>Application 
>>>
>>>Service
>>>
>>>ContextThemeWapper 
>>>>Activity 

###Application的创建过程
ActivityThread # performLaunchActivity 
```
Application app = r.packageInfo.makeApplication(false, mInstrumentation); 
//  r ActivityClientRecord 
// r.packageInfo LoadedApk
// 创建Application
```
1. 加载类
2. 创建ContextImpl  
 ```
 
 ```
3. 通过反射创建


## BroadCast 机制
消息发布和订阅的事件驱动模型，以binder机制为基础
1.BroadCastReceiver的注册过程
app process -->system server process
ContextWrapper.registerReceiver()
ContextImpl.registerReceiver()
Intent intent = ActivityManager.getService().registerReceiver();

ActivityManagerService

2.广播发送过程


3.广播的接收



		