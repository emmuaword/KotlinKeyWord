package com.learn.routertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.learn.basemodule.config.*

@Route(path = RouteTestMain2)
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        queryIntent()
    }

    private fun queryIntent() {
        intent?.apply {
            Toast.makeText(
                this@MainActivity2,
                PARAM_ONE + getStringExtra(PARAM_ONE) + "\n" + PARAM_TWO + getIntExtra(
                    PARAM_TWO,
                    0
                ),
                Toast.LENGTH_SHORT
            )
                .show()
            putExtra(INTENT_SUCCESS,"跳转正确")
        }
        setResult(INTENT_SUCCESS_CODE,intent)
    }
}