package com.king.mobile.downloadlib

import android.view.ViewParent

open class SuperSampleClass {

    open fun getName() {

    }


}


// 主构造函数不能包含任何的代码。
// 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面
class SampleClass public constructor(param: Any) : SuperSampleClass() {
    init { // 实例化 时会优先执行
        // 初始化的代码可以放到以 init  关键字作为前缀的初始化块（initializer blocks）中。
        println(param.toString())
    }

    init {
        // 初始化的代码可以放到以 init  关键字作为前缀的初始化块（initializer blocks）中。
        println("over")
    }

    // 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，
    // 可以直接委托或者通过别的次构造函数间接委托。
    // 委托到同一个类的另一个构造函数用 this 关键字即可
    constructor (param1: Any, param2: Any) : this(param1) {

    }

    override fun getName() {
        val xiaoMing = Person("xiaoMing")
    }
}

class Person (name: String) {
    var children: MutableList<Person> = mutableListOf();

    constructor(name: String,parent: Person): this(name) { // 人生而有父母
        parent.children.add(this) //
    }
}
