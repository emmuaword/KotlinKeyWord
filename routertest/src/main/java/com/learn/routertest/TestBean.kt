package com.learn.routertest

import java.io.Serializable

/**
 * @author TW
 * @date 2020/5/21 11:46
 * @description
 * @mail 2278671454@qq.com
 */
data class TestBean(
    var name: String = "",
    var age: Int = 18,
    var childOne: ChildOne? = ChildOne()
) : Serializable {
    fun function1() {
        println("function1\n")
    }

    fun function2() {
        println("function2\n")
    }

    fun setListener(listener: CallBack) {
        listener.getData("李四")
    }

    data class ChildOne(var args: String = "args") : Serializable

    class ChildTwo {
        var arg2: String = "arg2"
    }
}