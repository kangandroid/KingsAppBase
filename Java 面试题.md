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




##虚拟机原理
###内存分区
###GC机制
1.GC机制
2.GC算法
3.

###类加载机制
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