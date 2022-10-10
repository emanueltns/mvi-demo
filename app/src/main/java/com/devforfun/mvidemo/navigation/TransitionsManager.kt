package com.devforfun.mvidemo.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TransitionsManager constructor() {

    val defaultTransition = TransitionEffect(
        { slideInHorizontally { 1000 } },
        { slideOutHorizontally { -1000 } },
        { slideInHorizontally { -1000 } },
        { slideOutHorizontally { 1000 } },
    )

    private val _effectsFlow: MutableStateFlow<TransitionEffect> = MutableStateFlow(defaultTransition)
    val effectsFlow: Flow<TransitionEffect> = _effectsFlow

    fun setHorizontalTransitions() {
        _effectsFlow.tryEmit(defaultTransition)
    }

    fun setFadeTransitions() {
        val effect = TransitionEffect(
            { fadeIn() },
            { fadeOut() },
            { fadeIn() },
            { fadeOut() }
        )
        _effectsFlow.tryEmit(effect)
    }
}

@OptIn(ExperimentalAnimationApi::class)
data class TransitionEffect constructor(
    val enter: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition,
    val exit: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition,
    val popEnter: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition,
    val popExit: AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition
)
