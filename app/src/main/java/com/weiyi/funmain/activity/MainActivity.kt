package com.weiyi.funmain.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.learn.basemodule.config.*
import com.learn.routertest.TestBean
import com.weiyi.funmain.R
import com.weiyi.funmain.funtest.CompareUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ComponentActivity() {
    val testBean = TestBean(name = "李四", age = 32, childOne = TestBean.ChildOne(args = "李四儿"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun routeStart(view: View) {
        ARouter.getInstance()
            .build(RouteTestMain2)
            .withString(PARAM_ONE, "参数1")
            .withInt(PARAM_TWO, 1)
            .withSerializable(CUSTOM_OBJ, testBean)
            .navigation(this, INTENT_SUCCESS_CODE)
    }

    fun webJump(view: View) {
        ARouter.getInstance()
            .build(WebviewActivity)
            .withString(URL, "file:///android_asset/test.html")
            .navigation()
    }

    fun flutterJump(view: View) {
        ARouter.getInstance()
            .build(AppMainSecond)
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

    fun compare(view: View) {
        Toast.makeText(
            this@MainActivity,
            "${CompareUtil.compareNumTo(et_num1.text.toString(), et_num2.text.toString())}",
            Toast.LENGTH_SHORT
        ).show()

    }

    fun getVersionCode(view: View) {
        Toast.makeText(this, "${CompareUtil.getVersionCode(this)}", Toast.LENGTH_SHORT).show()
    }

}
