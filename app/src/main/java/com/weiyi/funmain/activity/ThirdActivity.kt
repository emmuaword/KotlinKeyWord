package com.weiyi.funmain.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.learn.basemodule.config.AppMainThird
import com.learn.basemodule.utils.CompareUtil
import com.learn.basemodule.utils.DeviceUtils
import com.weiyi.funmain.R
import kotlinx.android.synthetic.main.activity_third.*

/**
 * @author:tw
 * @date: 2021年7月2日16:05:44
 * @email：2278671454@qq.com
 * @description: 第三个页面，flutter调用新开页传参用
 */
@Route(path = AppMainThird)
class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    fun printScreenWidth(view: View) {
        Toast.makeText(
            this,
            "getScreenWidth:${DeviceUtils.getScreenWidth(this)}\nscreenWidth:${
                DeviceUtils.screenWidth(this)
            }",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun printScreenHeight(view: View) {
        Toast.makeText(
            this,
            "getScreenRealHeight1:${DeviceUtils.getScreenRealHeight1(this)}\n" +
                    "getScreenRealHeight2:${DeviceUtils.getScreenRealHeight2(this)}\n" +
                    "getScreenHeight:${DeviceUtils.getScreenHeight(this)}\n" +
                    "screenHeight:${DeviceUtils.screenHeight(this)}",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun compare(view: View) {
        Toast.makeText(
            this,
            "${CompareUtil.compareNumTo(et_num1.text.toString(), et_num2.text.toString())}",
            Toast.LENGTH_SHORT
        ).show()

    }

    fun getVersionCode(view: View) {
        Toast.makeText(this, "${CompareUtil.getVersionCode(this)}", Toast.LENGTH_SHORT).show()
    }
}