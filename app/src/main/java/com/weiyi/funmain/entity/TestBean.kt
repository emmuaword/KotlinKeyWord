package com.weiyi.funmain.entity

import com.weiyi.funmain.CallBack

/**
 * @author TW
 * @date 2020/5/21 11:46
 * @description
 * @mail 2278671454@qq.com
 */
class TestBean {
    var name: String = ""
    var age: Int = 18
    var childOne: ChildOne? = ChildOne()
    fun function1() {
        println("function1\n")
    }

    fun function2() {
        println("function2\n")
    }

    fun setListener(listener:CallBack){
        listener.getData("李四")
    }

    class ChildOne {
        var args: String = "args"
    }

    class ChildTwo {
        var arg2: String = "arg2"
    }
}