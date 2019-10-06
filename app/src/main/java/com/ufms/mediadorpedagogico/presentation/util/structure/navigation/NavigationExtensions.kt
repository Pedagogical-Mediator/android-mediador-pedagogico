package com.ufms.mediadorpedagogico.presentation.util.structure.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.navigateSafe(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId)
    action?.let {
        navigate(resId, args, navOptions, navExtras)
    }
}

fun NavController.navigateSafe(directions: NavDirections) {
    val resId = directions.actionId
    val action = currentDestination?.getAction(resId)
    if (action != null) navigate(directions)
}