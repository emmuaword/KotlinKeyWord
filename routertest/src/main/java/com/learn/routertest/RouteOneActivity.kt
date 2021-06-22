package com.learn.routertest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.learn.basemodule.config.RouteTestMain2

class RouteOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.init(application)
        ARouter.openLog()
        ARouter.openDebug()
    }

    fun jumptest(view: View) {
        ARouter.getInstance()
            .build(RouteTestMain2)
            .navigation()
    }
}