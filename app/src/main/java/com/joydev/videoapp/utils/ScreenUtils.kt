package com.joydev.videoapp.utils

import android.app.Activity
import android.graphics.Point

/**
 * Get Screen Width
 */
fun getScreenWidth(activity: Activity): Int = getScreenPoint(activity).x

private fun getScreenPoint(activity: Activity): Point {
    val display = activity.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)

    return size
}