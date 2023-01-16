package com.kkk.justodo.util

import android.util.Log
import android.view.View
abstract class DoubleClickListener : View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(v: View) {
        onSingleClick(v)
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
            Log.i("kkkCat-doubleClick", "lastClickTime: $lastClickTime, ckicTime: $clickTime, Diff: ${clickTime-lastClickTime}")
            onDoubleClick(v)
            lastClickTime = 0
        }


        lastClickTime = clickTime
    }
    abstract fun onDoubleClick(v: View)
    abstract fun onSingleClick(v: View)
    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
    }
}