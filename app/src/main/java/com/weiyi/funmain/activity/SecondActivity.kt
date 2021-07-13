package com.weiyi.funmain.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.learn.basemodule.config.AppMainSecond
import com.learn.basemodule.config.AppMainThird
import com.learn.basemodule.config.CHANNEL_TO_FLUTTER
import com.learn.basemodule.config.CHANNEL_TO_NATIVE
import com.weiyi.funmain.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.renderer.FlutterUiDisplayListener
import io.flutter.plugin.common.MethodChannel
import kotlinx.android.synthetic.main.activity_second.*


/**
 * @author:tw
 * @date: 2021年6月25日10:38:41
 * @email：2278671454@qq.com
 * @description: flutter 模块页面承载页
 */
@Route(path = AppMainSecond)
class SecondActivity : FlutterActivity() {
    private lateinit var mFlutterEngine: FlutterEngine
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        mFlutterEngine = FlutterEngine(this)
        createFlutterView()
    }


    private fun createFlutterView() {
        // 通过FlutterView引入Flutter编写的页面
        val flutterView = FlutterView(this)
        val lp = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )


        val initialRoute = "route1"
        mFlutterEngine.navigationChannel.setInitialRoute(initialRoute)
        mFlutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        // 关键代码，将Flutter页面显示到FlutterView中
        flutterView.attachToFlutterEngine(mFlutterEngine)
        fl_container.addView(flutterView, lp)

        acceptFlutterInfo()
        val flutterUiDisplayListener = object : FlutterUiDisplayListener {
            override fun onFlutterUiDisplayed() {
                fl_container.visibility = View.VISIBLE
            }

            override fun onFlutterUiNoLongerDisplayed() {
//                fl_container.visibility = View.INVISIBLE
            }
        }

        flutterView.addOnFirstFrameRenderedListener(flutterUiDisplayListener)
    }


    private fun acceptFlutterInfo() {
        val nativeChannel = MethodChannel(mFlutterEngine.dartExecutor, CHANNEL_TO_NATIVE)
        nativeChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "backSecondNative" -> result.success("收到来自Flutter的消息")
                "openNextNative" -> {
                    ARouter.getInstance()
                        .build(AppMainThird)
                        .navigation()
                }
                else -> result.notImplemented()
            }
        }

    }


    fun sendFlutterInfo(view: View) {
        val infoMap = mutableMapOf<String, String>()
        infoMap["message"] = "这是传给flutter的消息"
        val flutterChannel = MethodChannel(mFlutterEngine.dartExecutor, CHANNEL_TO_FLUTTER)
        flutterChannel.invokeMethod("onActivityResult", infoMap)
    }
}