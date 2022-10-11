package com.devforfun.mvidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.devforfun.mvidemo.main.view.MainScreenView
import com.devforfun.mvidemo.navigation.AppNavigator
import com.devforfun.mvidemo.navigation.Destination
import com.devforfun.mvidemo.personaldata.view.PersonalDataScreenView
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var navController: NavController? = null

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R.string.app_name
            MVIDemoApp(appNavigator = AppNavigator(rememberAnimatedNavController()))
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun MVIDemoApp(appNavigator: AppNavigator) {
        navController = appNavigator.navController
        val startDestination = Destination.MainScreen.route
        AnimatedNavHost(
            appNavigator.navController,
            startDestination = startDestination
        ) {
            composable(route = Destination.MainScreen.route) {
                MainScreenView(appNavigator = appNavigator)
            }

            composable(route = Destination.PersonalData.route) {
                PersonalDataScreenView(personalNavigation = appNavigator)
            }
        }
    }
}
