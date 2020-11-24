package com.tw.simpleresult.constract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.activity.result.contract.ActivityResultContract
import com.tw.simpleresult.MainActivity
import com.tw.simpleresult.SecondActivity

class CustomContract : ActivityResultContract<String?, String?>() {


    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, SecondActivity::class.java).apply { putExtra(MainActivity.NAME, input) }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val result: String? = intent?.getStringExtra(SecondActivity.RESULT)
        return if (resultCode == Activity.RESULT_OK && TextUtils.isEmpty(result).not()) {
            result
        } else {
            ""
        }
    }
}