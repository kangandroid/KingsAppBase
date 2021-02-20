# Kotlin

### 使用 Kotlin 进行 Android 开发
使用 Kotlin 进行 Android 开发，可以受益于：

1. 代码更少、可读性更强。花更少的时间来编写代码与理解他人的代码。

1. 成熟的语言与环境。自 2011 年创建以来，Kotlin 不仅通过语言而且通过强大的工具在整个生态系统中不断发展。 现在，它已无缝集成到 Android Studio 中， 并被许多公司积极用于开发 Android 应用程序。

1. Android Jetpack 与其他库中的 Kotlin 支持。KTX 扩展 为现有的 Android 库添加了 Kotlin 语言特性，如协程、扩展函数、lambdas 与命名参数。
与 Java 的互操作性。可以在应用程序中将 Kotlin 与 Java 编程语言一起使用， 而无需将所有代码迁移到 Kotlin。

1. 支持多平台开发。不仅可以使用 Kotlin 开发 Android，还可以开发 iOS、后端与 Web 应用程序。 享受在平台之间共享公共代码的好处。
代码安全。更少的代码与更好的可读性导致更少的错误。Kotlin 编译器检测这些剩余的错误，从而使代码安全。

1. 易学易用。Kotlin 非常易于学习，尤其是对于 Java 开发人员而言。

1. 大社区。Kotlin 得到了社区的大力支持与许多贡献，该社区在全世界范围内都在增长。

### 类  class
1. Kotlin 中使用关键字 class 声明类
	class Person { /*……*/ }
2. 构造函数
	主构造函数【可选 一个】
	次构造函数【可选 多个】
	
	class Person **constructor(firstName: String)** { /*……*/ }
	
3. 初始化块


### 对象  object   companion
对象表达式：object：AClass { /...../ }
对象声明： object AClass { /***/}
伴生对象：



### 内联类 关键字 inline
	必须有唯一一个属性在主构造函数中初始化，不能含有init的代码块 不能有幕后字段
### 委托 by 
委托模式是实现继承的一个很好的替代方案，

### 类型别名 typealias 
 用于简化泛型类 `typealias B = SC`
## 函数
### 尾递归函数  tailrec 
Kotlin 支持一种称为尾递归的函数式编程风格。 这允许一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险。 当一个函数用 tailrec 修饰符标记并满足所需的形式时，编译器会优化该递归，留下一个快速而高效的基于循环的版本：要符合 tailrec 修饰符的条件的话，函数必须将其自身调用作为它执行的最后一个操作。在递归调用后有更多代码时，不能使用尾递归，并且不能用在 try/catch/finally 块中。目前在 Kotlin for JVM 与 Kotlin/Native 中支持尾递归。

### 函数表达式

### 高阶函数与 lambda 表达式
高阶函数是将函数用作参数或返回值的函数。
函数类型
函数类型实例化 
函数类型实例调用 f.invoke(x) 
lambda 表达式  ： val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

### 闭包
Lambda 表达式或者匿名函数（以及局部函数和对象表达式） 可以访问其 闭包 ，即在外部作用域中声明的变量。 在 lambda 表达式中可以修改闭包中捕获的变量：
### 协程
#### 基础使用 GlobalScope.launch

	GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }
    println("Hello,") // 协程已在等待时主线程还在继续
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
>    delay 是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程，并且只能在协程中使用。

#### 桥接阻塞与非阻塞的世界 runBlocking
 调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
 
 	import kotlinx.coroutines.*
	fun main() = runBlocking<Unit> { // 开始执行主协程
	    GlobalScope.launch { // 在后台启动一个新的协程并继续
	        delay(1000L)
	        println("World!")
	    }
	    println("Hello,") // 主协程在这里会立即执行
	    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
	}
>    这里的 runBlocking<Unit> { …… } 作为用来启动顶层主协程的适配器。 我们显式指定了其返回类型 Unit，因为在 Kotlin 中 main 函数必须返回 Unit 类型。

####等待一个作业： join（）
延迟一段时间来等待另一个协程运行并不是一个好的选择。使用join 实现相同效果：

	val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
	    delay(1000L)
	    println("World!")
	}
	println("Hello,")
	job.join() // 等待直到子协程执行结束
	
####结构化的并发
使用` GlobalScope.launch `创建一个顶层协程，消耗一些内存资源 会导致内存泄漏。我们使用 runBlocking 协程构建器将 main 函数转换为协程。在这个协程作用域内，启动协程，无需显式调用 join。
	
	import kotlinx.coroutines.*
	fun main() = runBlocking { // this: CoroutineScope
	    launch { // 在 runBlocking 作用域中启动一个新协程
	        delay(1000L)
	        println("World!")
	    }
	    println("Hello,")
	}

####作用域构建器    
可以使用 coroutineScope 构建器声明自己的作用域。它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。

	import kotlinx.coroutines.*

	fun main() = runBlocking { // this: CoroutineScope
	    launch { 
	        delay(200L)
	        println("Task from runBlocking")
	    }
	    coroutineScope { // 创建一个协程作用域
	        launch {
	            delay(500L) 
	            println("Task from nested launch")
	        }
	    
	        delay(100L)
	        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
	    } 
	    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
	}

> unBlocking 与 coroutineScope可能看起来很类似，因为它们都会等待其协程体以及所有子协程结束。 主要区别在于，runBlocking 方法会阻塞当前线程来等待， 而 coroutineScope 只是挂起，会释放底层线程用于其他用途。 由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。

#### 提取函数重构
我们来将 launch { …… } 内部的代码块提取到独立的函数中。当你对这段代码执行“提取函数”重构时，你会得到一个带有 suspend 修饰符的新函数。
	
	import kotlinx.coroutines.*

	fun main() = runBlocking {
	    launch { doWorld() }
	    println("Hello,")
	}
	
	// 这是你的第一个挂起函数
	suspend fun doWorld() {
	    delay(1000L)
	    println("World!")
	}
