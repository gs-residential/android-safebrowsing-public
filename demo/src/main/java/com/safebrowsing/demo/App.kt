package com.safebrowsing.demo

import android.app.Application
import com.sclpfybn.safebrowsing.monitoring.Logger as LibLogger
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        timber()
    }

    private fun timber() {
        Timber.plant(Timber.DebugTree())
        LibLogger.init(Logger())
    }
}
