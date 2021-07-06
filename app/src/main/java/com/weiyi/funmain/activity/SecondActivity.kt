package com.weiyi.funmain.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.learn.basemodule.config.AppMainSecond
import com.learn.basemodule.config.CHANNEL_TO_FLUTTER
import com.learn.basemodule.config.CHANNEL_TO_NATIVE
import com.weiyi.funmain.R
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import kotlinx.android.synthetic.main.activity_second.*


/**
 * @author:tw
 * @date: 2021年6月25日10:38:41
 * @email：2278671454@qq.com
 * @description: flutter 模块页面承载页
 */
@Route(path = AppMainSecond)
class SecondActivity : ComponentActivity() {
    private lateinit var flutterEngine: FlutterEngine
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        flutterEngine = FlutterEngine(this)
        createFlutterView()
    }


    private fun createFlutterView() {
        // 通过FlutterView引入Flutter编写的页面
        val flutterView = FlutterView(this)
        val lp = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )


        val initialRoute = "route1?{\"message\":\"向flutter传递初始数据\"}"
        flutterEngine.navigationChannel.setInitialRoute(initialRoute)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        // 关键代码，将Flutter页面显示到FlutterView中
        flutterView.attachToFlutterEngine(flutterEngine)
        fl_container.addView(flutterView, lp)

        acceptFlutterInfo()

    }


    private fun acceptFlutterInfo() {
        val nativeChannel = MethodChannel(flutterEngine.dartExecutor, CHANNEL_TO_NATIVE)
        nativeChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "backFirstNative" -> result.success("收到来自Flutter的消息")
                else -> result.notImplemented()
            }
        }

    }


    private fun sendFlutterInfo() {
        val infoMap = mutableMapOf<String, String>()
        infoMap["message"] = "这是传给flutter的消息"
        val flutterChannel = MethodChannel(flutterEngine.dartExecutor, CHANNEL_TO_FLUTTER)
        flutterChannel.invokeMethod("onActivityResult", infoMap)
    }
}