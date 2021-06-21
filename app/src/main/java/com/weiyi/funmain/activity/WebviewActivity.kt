package com.weiyi.funmain.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.learn.basemodule.config.URL
import com.learn.basemodule.config.WebviewActivity
import com.weiyi.funmain.R
import kotlinx.android.synthetic.main.activity_webview.*

@Route(path = WebviewActivity)
class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        intent.getStringExtra(URL)?.let { web_jump.loadUrl(it) }
    }
}