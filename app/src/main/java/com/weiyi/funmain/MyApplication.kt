package com.weiyi.funmain

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import me.jessyan.autosize.AutoSize

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