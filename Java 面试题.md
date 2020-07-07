##java基础
1.基础数据类型与大小
byte(8bit) char(16bit) boolean(8bit/32bit) short(16bit) int(32bit) long(64bit) float(32bit) double(64bit)
2.引用类型与使用场景
a.强引用 new 对象的引用
b.软引用
c.弱引用
d.虚引用
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
###内存分区
堆  、方法区、VM栈  、本地方法栈、  程序计数器

###GC机制
1.GC机制
2.GC算法
3.

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

###内存模型

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
	* 实现RUnnable接口
	* Callable 返回Future对象
	* 使用线程池创建()
3. 线程的一些方法
####2.线程池
####3.锁