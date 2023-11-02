package com.auro.application.core.platform

import android.util.Log


var isLogging= false
fun checkLogs(logging: Boolean?) : Boolean {
    return (isLogging != null).also {
        if (logging != null) {
            isLogging = logging
        }
    }
}

fun Any.logd(message: String) {
    if (isLogging) {
        Log.d(this::class.java.simpleName, message)
    }
}

fun Any.loge(message: String) {
    if (isLogging) {
        Log.e(this::class.java.simpleName, message)
    }
}

fun Any.logi(message: String) {
    if (isLogging) {
        Log.i(this::class.java.simpleName, message)
    }
}

fun Any.logv(message: String) {
    if (isLogging) {
        Log.v(this::class.java.simpleName, message)
    }
}

fun Any.logw(message: String) {
    if (isLogging) {
        Log.w(this::class.java.simpleName, message)
    }
}