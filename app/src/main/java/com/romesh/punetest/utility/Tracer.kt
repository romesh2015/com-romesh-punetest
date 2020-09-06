package com.romesh.punetest.utility
import android.util.Log

/**
 * In this class we have written the common functions to handle the logs in whole app.
 */
object Tracer {
    private const val LOG_ENABLE = true
    fun debug(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            if (message != null) {
                Log.d(Config.App_name + TAG, message)
            }
        }
    }
    @JvmStatic
    fun info(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            if (message != null) {
                Log.i(Config.App_name + TAG, message)
            }
        }
    }
    @JvmStatic
    fun warning(TAG: String, message: String?) {
        if (LOG_ENABLE) {
            if (message != null) {
                Log.w(Config.App_name + TAG, message)
            }
        }
    }
}