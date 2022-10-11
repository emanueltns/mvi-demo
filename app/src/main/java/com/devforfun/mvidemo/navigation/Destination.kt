package com.devforfun.mvidemo.navigation

sealed class Destination(val route: String) {
    object MainScreen : Destination("MainScreen")
    object PersonalData: Destination("PersonalData")
}
