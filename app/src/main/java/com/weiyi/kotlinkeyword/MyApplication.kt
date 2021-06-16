package com.weiyi.kotlinkeyword

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author: tw
 * @date: 2021/6/15 0015 10:11
 * @email：2278671454@qq.com
 * @description:
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }
}