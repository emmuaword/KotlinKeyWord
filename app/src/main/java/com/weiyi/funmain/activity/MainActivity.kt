package com.weiyi.funmain.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.learn.basemodule.config.*
import com.weiyi.funmain.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun routeStart(view: View) {
        ARouter.getInstance()
            .build(RouteTestMain2)
            .withString(PARAM_ONE, "参数1")
            .withInt(PARAM_TWO, 1)
            .navigation(this, INTENT_SUCCESS_CODE)
    }

    fun webJump(view: View) {
        ARouter.getInstance()
            .build(WebviewActivity)
            .withString(URL, "file:///android_asset/test.html")
            .navigation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            INTENT_SUCCESS_CODE -> {
                tv_intent_success.text = data?.getStringExtra(INTENT_SUCCESS).orEmpty()
            }
        }
    }

}
