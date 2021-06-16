package com.weiyi.funmain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.learn.basemodule.config.AppMainSecond
import com.learn.basemodule.config.RoomLearnMain


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun roomStart(view: View) {
        ARouter.getInstance().build(AppMainSecond).navigation()
    }
}
