package com.tw.simpleresult

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tw.simpleresult.constract.CustomContract
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : Activity() {
    companion object {
        val RESULT: String = "result"

    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra(MainActivity.NAME)
        tv_receive.text = "接收到的数据为：$name"
        tv_jump.apply {
            setOnClickListener { finish() }
        }
    }

    override fun finish() {
        val intent = Intent().apply {
            putExtra(RESULT, "Hello，first，I'm result！")
        }
        setResult(Activity.RESULT_OK, intent)
        super.finish()
    }
}