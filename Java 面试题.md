##java基础
1.基础数据类型与大小
byte(8bit) 一个字节 8个二进制位 内存的最小单位 bit  1kb = 1024bit
char(16bit)
boolean(8bit/32bit)
short(16bit)
int(32bit)
long(64bit)
float(32bit)
double(64bit)

图片的像素 pxSize
ARGB_8888 32bit
ARGB_4444 16bit
RGB_565   16bit
ALPHA_8   8bit
BitMap在内存中的大小 width * height * pxSize

3.数据结构集合框架实现原理
List
    ArrayList
    LinkedList
Set
    HashSet
    TreeSet
Map
    HashMap
    TreeMap
4.


##Object类 万物之源
###方法
#### getClass
获取当前对象的字节码
#### hashCode
返回对象的哈希代码值。支持此方法的好处是散列表
#### toString
	 public String toString() {
       	 return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

#### wait
***native方法：***使当前线程等待直到另一个线程调用此对象Object.notify（）或Object.notifyAll（）方法，或其他某个线程中断当前线程，或等待时间已结束。
#### notify
***native方法：*** 唤醒等待此对象监视器的单个线程。如果有任何线程正在等待这个对象，则选择其中一个线程被唤醒。选择是任意的，由实现者自行决定。线程通过调用{@code wait}方法之一来等待对象的监视器
#### notifyAll
*native方法* 清除此对象监视器上等待的所有线程。线程通过调用{@code wait}方法之一来等待对象的监视器
#### equals

 	public boolean equals(Object obj) {
        	return (this == obj);
    	}
#### finalize
对象回收时回调用

#### equals与==的区别

**==**的作用是判断两个对象的地址是不是相等，即判断两个对象是不是同一个对象，(基本数据类型==比较的是值，引用数据类型==比较的是内存地址) equals要看具体实现。 Object equals和==是相同的。
##虚拟机原理



###JVM 内存分区
线程共享 主存 工作内存
1. 堆(heap) 存放对象实例 根据对象的生命周期分代管理，便于GC。
2. 方法区 类 永久代 加载的类的信息，常量，静态变量，JIT后的代码。
线程私有 生命周期与线程同步
3. VM Stack  单元结构为栈帧 存储局部变量表，操作栈数，动态链接，方法出口
4. Native Method Stack 存储native方法的信息
5. 程序计数器 线程执行字节码的行号指示器。
直接内存 使用NIO可直接读写

###GC机制
1.GC算法
    a.标记-清除算法，占用空间小，效率低，产生内存碎片
    b.复制算法，简单高效，但需要等量内存担保，得到连续的内存，存活对象变多时，效率会下降
    c.标记整理算法， 先标记除要回收的对象，然后将存活对象向一端靠拢，然后清理端边界以外的内存
    d.分代回收算法，根据存活对象的生命周期长短将内存分为几块区域，
        JVM堆内存分为 新生代和老年代 新生代采用复制算法，老年代使用标记整理算法。

2.引用类型与使用场景
    a.强引用 直接指向实例对象的引用。 引用存在就不可被回收。
    b.软引用 指向SoftReference包裹对象的引用，但内存一出之前，GC回收软引用.
        做内存缓存，如图片缓存
    c.弱引用 指向WeakReference包裹对象的引用，下一次GC到来时会被回收，不论是否面临内存溢出，
        在静态内部类中，经常会使用虚引用。防止内存泄漏。
    d.虚引用 指向PhantomReference包裹对象的引用，获取不到对象实例

3.方法区的回收：
    废弃的常量，常量的引用不存在时会被回收。
    无用的类，类的实例不存在，类的字节码没有被引用，类的加载器已被回收。

4.GC Roots
    a.VM Stack中引用的对象
    b.Native method Stack中的引用对象
    c.方法区中的静态属性引用的对象
    d.方法区中的常量对象
    GC Root不可达的对象将会被标记回收



###类加载机制
**类加载过程**

加载 -- 验证 -- 准备 -- 解析 -- 初始化 -- 使用 --卸载

加载时机：
	1. new 创建对象或调用类的静态成员时
	2. 通过反射调用
	3. 子类初始化时会先加载父类
	4. main方法所使用到的类
	5. 动态语言使用

**双亲委派机制**



1.类加载过程
2.加载时间
3.双亲委派机制
4.打破双亲委派机制



###并发
指令重排序，JVM 为了优化程序执行过程。编译时改变指令的执行顺序。
volatile (易变的) 保证变量对所有线程可见性每次调用时都会去主内存中read

线程的实现

线程的调度
1.协同式线程调度
2.抢占式线程调度（java线程调度方式）
状态转换
new Thread
1.线程创建 start()
2.准备就绪，只差cpu执行权
3.run() 在run方法中执行
4.waiting 不占用cpu，等待其他线程唤醒 Object.await()
5.
线程安全

锁优化

主内存 到 工作内存，对象创建以后保存在堆内存中。当有子线程调用对象时，是会先住内存的值到工作内存中，使用完成后会写回的主内存中去。

####1.线程
1. 线程的状态
2. Java线程创建的几种方式
	* 继承Thread类
	* 实现Runnable接口
	* Callable 返回Future对象
	* 使用线程池创建()
3. 线程的一些方法

####2.线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        核心线程数和最大线程数都为1
            public static ExecutorService newSingleThreadExecutor() {
                return new FinalizableDelegatedExecutorService
                    (new ThreadPoolExecutor(1, 1,
                                            0L, TimeUnit.MILLISECONDS,
                                            new LinkedBlockingQueue<Runnable>()));
            }
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(mThreadCount);
            public static ExecutorService newFixedThreadPool(int nThreads) {
                    return new ThreadPoolExecutor(nThreads, nThreads,
                                                  0L, TimeUnit.MILLISECONDS,
                                                  new LinkedBlockingQueue<Runnable>());
            }
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            public static ExecutorService newCachedThreadPool() {
                return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                              60L, TimeUnit.SECONDS,
                                              new SynchronousQueue<Runnable>());
            }
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(coreThreadCount);

        ExecutorService workStealingPool = Executors.newWorkStealingPool();





####3.锁
公平锁/非公平锁  公平锁是多线程模式下按照申请顺序来获取锁,排队获取锁。非公平锁但锁处于处
乐观锁/悲观锁    悲观锁在多线程并发模式下一定会出现线程安全问题,

类锁和对象锁
