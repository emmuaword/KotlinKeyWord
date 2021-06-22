package com.learn.routertest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.learn.basemodule.config.*

@Route(path = RouteTestMain2)
class RouteTwoActivity : AppCompatActivity() {
//    @Autowired
    var custom_object: TestBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        queryIntent()
    }

    private fun queryIntent() {
        intent?.apply {
            Toast.makeText(
                this@RouteTwoActivity,
                PARAM_ONE + getStringExtra(PARAM_ONE) + "\n" + PARAM_TWO + getIntExtra(
                    PARAM_TWO,
                    0
                ) + "\n" + CUSTOM_OBJ + getSerializableExtra(CUSTOM_OBJ)?.let { (it as TestBean).name },
                Toast.LENGTH_SHORT
            )
                .show()
            putExtra(INTENT_SUCCESS, "跳转正确")
        }
        setResult(INTENT_SUCCESS_CODE, intent)
    }
}