package com.devforfun.mvidemo.navigation

import androidx.annotation.VisibleForTesting
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.devforfun.mvidemo.main.viewmodel.MainNavigation
import com.devforfun.mvidemo.personaldata.viewmodel.PersonalNavigation

open class AppNavigator constructor(
    val navController: NavHostController,
    private val transitionsManager: TransitionsManager? = null
    ) : MainNavigation, PersonalNavigation {

    override fun moveBack() {
        navigateBack()
    }

    override fun navigateToPersonalDataScreen() {
       navigateTo(Destination.PersonalData)
    }

    private fun navigateTo(destination: Destination, singleTop: Boolean = false, vararg args: Pair<String, String?>) {
        applyTransitions(destination, navController.currentBackStackEntry)
        navControllerNavigateTo(destination, singleTop, *args)
    }

    private fun navigateBack() {
        navControllerPopBackStack()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun navControllerNavigateTo(destination: Destination, singleTop: Boolean, vararg args: Pair<String, String?>) {
        navController.navigate(destination.route) {
            launchSingleTop = singleTop
        }
        putArgsIfNecessary(*args)
    }

    private fun applyTransitions(destination: Destination? = null, navBackStackEntry: NavBackStackEntry?) {
        val prevRoute = navBackStackEntry?.destination?.route
        if (destination == Destination.MainScreen || prevRoute == Destination.MainScreen.route) {
            transitionsManager?.setFadeTransitions()
        } else {
            transitionsManager?.setHorizontalTransitions()
        }
    }

    private fun putArgsIfNecessary(vararg args: Pair<String, String?>) {
        if (args.isNotEmpty()) {
            navController.currentBackStackEntry?.arguments.apply {
                args.forEach { (key: String, arg: String?) ->
                    this?.putString(key, arg)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun navControllerPopBackStack() {
        navController.popBackStack()
    }
}
