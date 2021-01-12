# Android Framework

## Android 系统的启动
1. 按电源健，通电启动引导程序BootLoader。
2. BootLoader拉起OS启动Linux内核。
3. Linux内核启动，设置缓存、计划表，加载驱动，寻找init，启动init进程。
4. init进程的启动 
5. zygote进程的启动，
6. SystemServer进程的启动
7. Launcher app的启动

### init进程  （init.rc）
注册属性服务 ---adb shell getprop 获取系统的属性服务（相当于注册表）
属性在共享内存上可以IPC，如此客户端可以存取。
启动zygote 和 重启zygote。

### zygote进程的启动
> zygote进程是虚拟机实例，可通过复制自身来创建系统服务进程及应用进程，zygote进程于系统服务进程是通过socket通信来实现进程的创建的。被复制创建的进程中关闭server socket

1. 创建AppRuntime调用start启动进程。(native)
2. 创建VM，注册JNI，通过JNI 调用ZygoteInit的main方法进入VM的Java框架层(native->java)
3. 通过registerZygoteSocket创建ServerSocket，预加载类（加载类较多耗时）和资源，(java)
4.   Zygote.forkSystemServer 启动SystemServer进程的(java) 
5. runSelectLoop来启动服务监听，等待AMS创建的进程的请求 (java)
6. AMS 发起创建进程的请求 zygote会获得一个ZygoteConnection然后调用runOnce 获取参数
7.  Zygote.forkAndSpecialize 创建进程
8.  fork的进程 最后执行 handleParentProc 继续RuntimeInit.zygoteInit()

### SystemServer进程启动（pid==0）
> SystemServer进程主要负责创建和管理系统服务。

1.  SystemServer进程 创建完成后  调用 handleSystemServerProcess开始自己的职责。
	1. 关闭 继承的ServerSoket。 
2. ZygoteInit.zygoteInit/ZygoteInit.main
	1. commonInit 设置时间/设置默认userAgent
	2. nativeZygoteInit/nativeFinishInit 启动Binder线程池
	2. applicationInit 通过反射吊起SystemServer 的静态 main方法
3.  SystemServer的main方法new SystemServer实例并执行run
4.  run初始化设置各种选项 Looper.prepareMainLooper(); 
5.  创建SystemServiceManager 来启动和管理诸多系统服务，
6.  然后Start services
	* startBootstrapServices 引导服务 如：Installer、ActivityManagerService、PackageManagerService 、PowerManagerService 、RecoverySystemService 、LightsService 、DisplayManagerService、UserManagerService、OverlayManagerService
	* startCoreServices 核心服务 如：BatteryService 、UsageService、WebViewUpdateService、BinderCallsStatsService、
	* startOtherServices 其他服务 如：VibratorService、IStorageManager、NetworkManagementService、IpSecService、NetworkStatsService... WindowManagerService、InputManagerService... 
7. Looper.loop()启动消息循环; 抛出异常退出。

## Launcher App 的启动
> Android系统启动的最后一步是启动一个Home应用程序，这个应用程序用来显示系统中已经安装的应用程序，这个Home应用程序就叫做Launcher。

1.  PackageManager在创建过程中会用Installer安装所有应用。包括Launcher
2. 系统服务启动完成后 ActivityManagerService调用systemReady方法
3. 调用mStackSupervisor.resumeFocusedStackTopActivityLocked()
4. 调用mStackSupervisor.resumeFocusedStackTopActivityLocked()
5. systemReady调用startHomeActivityLocked 启动Launcher HomeActivity
6. 创建Intent addCategory  CATEGORY_HOME，通过PackageManager来找到launcher应用的

## User App 启动
