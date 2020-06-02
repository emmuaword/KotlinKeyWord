package com.weiyi.kotlinkeyword.funtest

import com.weiyi.kotlinkeyword.CallBack
import com.weiyi.kotlinkeyword.entity.TestBean

/**
 * @author TW
 * @date 2020/6/1 10:49
 * @description
 * @mail 2278671454@qq.com
 */
object KeyWordTest {
    private var testBean: TestBean? = TestBean()

    @JvmStatic
    fun main(args: Array<String>) {
        normal()
        testLet()
        testAlso()
        testWith()
        testRun()
        testApply()
        testTakeIf()
        testRepeat()
    }

    private fun lamada() {
//        testBean?.setListener(object : TestCallback<String> {
//            override fun getData(data: String) {
//                println(data)
//            }
//
//        })
        testBean?.setListener(CallBack { data -> println(data) })

    }

    private fun normal() {
        testBean?.function1()
        testBean?.function2()
        testBean?.name = "张三"
        testBean?.age = 100
    }

    private fun testLet() {
        var name = testBean?.let {
            it.function1()
            it.function2()
            it.name = "李四"
            it.name
        }
    }

    private fun testAlso() {
        var entity = testBean?.also {
            it.function1()
            it.function2()
            it.name = "李四"
        }
    }

    private fun testWith() {
        if (testBean != null) {
            with(testBean!!) {
                function1()
                function2()
            }
            var info: String = with(testBean!!) {
                "name:$name,age:$age"
            }
//            info = "name:${testBean!!.name},age:$${testBean!!.age}"
            println(info)
        }
    }

    private fun testRun() {
        var info: String? = testBean?.run {
            "name:$name,age:$age"
        }
        println(info)
    }

    private fun testApply() {
        testBean?.apply {
            name = "张三"
            age = 1
        }

        if (testBean != null) {
            if (testBean!!.childOne != null) {
                testBean!!.childOne!!.args = "李四"
            }
        }
        testBean?.apply {
            childOne?.apply {
                args = "李四"
            }
        }
    }

    private fun testTakeIf() {
        testBean?.takeIf {
            it.name == "张三" && it.age == 18
        }?.apply {
            name = "李四"
        }
        testBean?.takeUnless {
            it.name == "张三" && it.age == 18
        }?.apply {
            name = "李四"
        }
    }

    private fun testRepeat() {
        repeat(100) {
            println(testBean?.name)
        }
    }

}