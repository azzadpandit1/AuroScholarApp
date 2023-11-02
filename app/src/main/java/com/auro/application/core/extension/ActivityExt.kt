package com.auro.application.core.extension

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

inline fun FragmentActivity.beginTransaction(block: FragmentTransaction.() -> Unit) {
    block(supportFragmentManager.beginTransaction())
}

val Activity.screenWidth: Int
    get() = Point().apply {
        windowManager.defaultDisplay.getSize(this)
    }.x

val Activity.screenHeight: Int
    get() = Point().apply {
        windowManager.defaultDisplay.getSize(this)
    }.y

inline fun <reified T : Activity> Activity.startActivity() {
    val intent = Intent()
    intent.setClass(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startActivity(options: Bundle?) {
    val intent = Intent()
    intent.setClass(this, T::class.java)
    startActivity(intent, options)
}