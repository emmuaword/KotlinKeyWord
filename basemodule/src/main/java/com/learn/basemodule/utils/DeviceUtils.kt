package com.learn.basemodule.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @author: tw
 * @date: 2021/7/5 0005 15:08
 * @email：2278671454@qq.com
 * @description:
 */
object DeviceUtils {
    fun getScreenWidth(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    fun getScreenHeight(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    fun getScreenRealHeight1(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val dm = DisplayMetrics()
        display.getRealMetrics(dm)
        return dm.heightPixels
    }

    fun getScreenRealHeight2(activity: Activity): Int {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size)
        return size.y
    }

    fun screenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }


    fun screenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }


}