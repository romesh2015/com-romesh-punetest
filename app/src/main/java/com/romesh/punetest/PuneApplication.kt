package com.romesh.punetest
import android.app.Application
import android.content.Context
class PuneApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        wContext = this
    }
    companion object {
        private var wContext: Context? = null

        fun getsAppContext(): Context {
            return wContext!!.applicationContext
        }
    }
}