package com.weiyi.funmain.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

class SchemeFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        // 统一外部跳转的Uri，实现路由器统一分发，减少只依靠Intent属性匹配带来的安全风险
        ARouter.getInstance().build(uri).navigation(this, object : NavigationCallback {
            override fun onFound(postcard: Postcard?) {
                
            }

            override fun onLost(postcard: Postcard?) {
                
            }

            override fun onArrival(postcard: Postcard?) {
                finish()
            }

            override fun onInterrupt(postcard: Postcard?) {
                
            }
        })
    }
}