package com.safebrowsing.demo

import com.sclpfybn.safebrowsing.monitoring.LoggerApi
import timber.log.Timber

class Logger: LoggerApi {

    override fun v(tag: String, message: String) {
        Timber.tag(decorateTag(tag)).i(message)
    }

    override fun d(tag: String, message: String) {
        Timber.tag(decorateTag(tag)).d(message)
    }

    override fun e(tag: String, message: String, throwable: Throwable?) {
        Timber.tag(decorateTag(tag)).e(throwable, message)
    }

    private fun decorateTag(tag: String): String {
        val threadName = Thread.currentThread().name
        return "@@SB [$threadName] $tag"
    }
}
