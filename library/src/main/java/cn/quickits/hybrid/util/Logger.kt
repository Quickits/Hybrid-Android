package cn.quickits.hybrid.util

import android.util.Log


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 14:42
 **/
object Logger {

    private const val TAG = "Hybrid"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

}