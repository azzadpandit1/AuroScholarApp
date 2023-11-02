package com.auro.application.core.navigation

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

fun Fragment.navigateTo(destinationId: Int) {
    findNavController(this).navigate(destinationId)
}

fun Fragment.navigateTo(destinationId: Int, bundle: Bundle) {
    findNavController(this).navigate(destinationId, bundle)
}

fun NavController.safeNavigate(destinationId: Int, args: Bundle? = null) {
    val action = currentDestination?.getAction(destinationId) ?: graph.getAction(destinationId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(destinationId, args)
    }
}

fun View.navigate(@IdRes direction: Int, bundle: Bundle?) {
    findNavController(this.findFragment()).navigate(direction, bundle)
}


fun NavController.safeNavigate(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}

inline fun <reified T : Any> Fragment.safeNavArgs() = lazy {
    val navController = findNavController(this)
    val backStackEntry = navController.currentBackStackEntry
        ?: throw IllegalStateException("Fragment $this not associated with a NavController")
    val value = backStackEntry.savedStateHandle.get<T>(KEY)
    value ?: throw IllegalStateException("Argument for ${T::class.simpleName} is missing")
}
