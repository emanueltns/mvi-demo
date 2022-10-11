package com.devforfun.mvidemo.main.viewmodel

import com.devforfun.mvidemo.main.model.models.Car


sealed class MainAction

object NextActionClick: MainAction()
data class SelectedCarActionClick(val car: Car) : MainAction()

