package com.auro.application.core.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes resId: Int): View = LayoutInflater.from(context).inflate(resId, this, false)

val ViewGroup.children: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

operator fun ViewGroup.get(index: Int): View? = getChildAt(index)

operator fun ViewGroup.plusAssign(child: View) = addView(child)

operator fun ViewGroup.minusAssign(child: View) = removeView(child)

operator fun ViewGroup.contains(child: View) = indexOfChild(child) != -1