package com.learn.basemodule.utils

import android.content.Context
import android.text.TextUtils
import java.util.regex.Pattern

/**
 * @author: tw
 * @date: 2021/6/30 0030 15:28
 * @email：2278671454@qq.com
 * @description:
 */
object CompareUtil {
    /**
     * 正则判断是否为整形数字，包括浮点
     * @param value 被用来比较的数字
     */
    fun isNumber(value: String?): Boolean {
        if (value == null || TextUtils.isEmpty(value.trim())) {
            return false
        }
        // 该正则表达式可以匹配所有的数字 包括负数
        val pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?")
        return pattern.matcher(value).matches()
    }

    /**
     * 比较两个数字的大小
     * @param num1,num2 被比较的两个数字
     */
    fun compareNumTo(num1: String?, num2: String?): Boolean {
        var number1 = num1
        var number2 = num2
        return try {
            if (number1.isNullOrEmpty() || !isNumber(number1)) {
                number1 = "0"
            }
            if (number2.isNullOrEmpty() || !isNumber(number1)) {
                number2 = "0"
            }
            val value1 = number1.toDouble()
            val value2 = number2.toDouble()
            value1 > value2

            //            int length1 = num1.length();
            //            int length2 = num2.length();
            //
            //            if (length1 != length2) {
            //                return length1 > length2;
            //            } else {
            //                if (num1.compareTo(num2) > 0) {
            //                    return true;
            //                } else if (num1.compareTo(num2) < 0) {
            //                    return false;
            //                }
            //            }
            //            return false;
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


    /**
     * 获取应用程序版本code
     * @param context
     * @return 当前应用的版本code
     */
    @Synchronized
    fun getVersionCode(context: Context): Int {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            return packageInfo.versionCode
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return 0
    }
}