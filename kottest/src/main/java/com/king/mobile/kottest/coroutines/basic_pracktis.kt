package com.king.mobile.kottest.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
        coroutineScope {
            delay(500)
            println("你好")
        }
    }
    println("Hello,") // 协程已在等待时主线程还在继续

    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    runBlocking {
        delay(2000L) // 非阻塞
    }

}

// 调用了 runBlocking 的主线程会一直 阻塞 直到
fun main1() = runBlocking<Unit> {
    GlobalScope.launch {
        delay(1000L)
        println("World")
    }
    print("hello")
    delay(2000L)
}

suspend fun doJob() {
    var job = GlobalScope.launch {
        delay(1000L)
        print("world")
    }
    println("hello")
    // 等待执行
    job.join()
    // 取消任务
    job.cancel()
    coroutineScope {

    }

    val startTime = System.currentTimeMillis()
    val job2 = GlobalScope.launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
        var resouce = withTimeout(22){
            delay(50) // Delay for 50 ms
            Resource()
        }
        resouce.close()
    }

    measureTimeMillis{
        var threadLocal = Thread.currentThread();
        threadLocal.set("main")
        println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        val job = GlobalScope.launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
            println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
            yield()
            println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        job.join()
        println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job2.cancelAndJoin() // 取消一个作业并且等待它结束
    println("main: Now I can quit.")
}
var acquired = 0

class Resource {
    init { acquired++ } // Acquire the resource
    fun close() { acquired-- } // Release the resource
}