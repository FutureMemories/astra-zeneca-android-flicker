package se.futurememories.flickery.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class Screen(context: Context) {
    val x: Int
    val y: Int

    init {
        val display = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(display)
        x = display.widthPixels
        y = display.heightPixels
    }

}