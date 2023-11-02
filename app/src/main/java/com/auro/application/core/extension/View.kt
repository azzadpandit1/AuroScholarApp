/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.auro.application.core.extension

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.transition.Transition


fun View.getString(@StringRes resId: Int): String = resources.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String =
    resources.getString(resId, *formatArgs)

fun View.getDimenSize(@DimenRes resId: Int): Int =
    resources.getDimensionPixelSize(resId)

fun View.cancelTransition() {
    transitionName = null
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE


inline fun <T : View> T.afterMeasured(crossinline block: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                block()
            }
        }
    })
}

fun View.openKeyboard() {
    context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.closeKeyboard(): Boolean {
    return try {
        context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (_: RuntimeException) {
        false
    }
}

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun ImageView.loadUrlAndPostponeEnterTransition(url: String, activity: FragmentActivity) {
    val target = ImageViewBaseTarget(this, activity)
    Glide.with(context.applicationContext).load(url).into(target)
}

private class ImageViewBaseTarget(var imageView: ImageView?, var activity: FragmentActivity?) : BaseTarget<Drawable>() {
    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)

    override fun removeCallback(cb: SizeReadyCallback) {
        imageView = null
        activity = null
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        imageView?.setImageDrawable(resource)
        activity?.supportStartPostponedEnterTransition()
    }
}
