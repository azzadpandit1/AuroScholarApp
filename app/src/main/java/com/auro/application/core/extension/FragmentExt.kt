package com.auro.application.core.extension

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.auro.application.core.platform.BaseFragment

inline fun Fragment.supplyContext(block: Activity.() -> Unit) {
    activity?.run { block(this) }
}

fun Fragment.goBack() {
    findNavController().navigateUp()
}
fun Fragment.finish() {
    supplyContext { finish() }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

fun BaseFragment.close() = fragmentManager?.popBackStack()


val BaseFragment.appContext: Context get() = activity?.applicationContext!!
