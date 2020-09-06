@file:Suppress("DEPRECATION")
package com.romesh.punetest.utility
import android.content.Context
import android.net.ConnectivityManager
import com.romesh.punetest.PuneApplication
object Utility {
    // to check internet is available or not
    fun isConnected(): Boolean {
        var connected = false
        try {
            val cm =
                PuneApplication.getsAppContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            Tracer.warning("Connectivity Exception", e.message)
        }
        return connected
    }
}