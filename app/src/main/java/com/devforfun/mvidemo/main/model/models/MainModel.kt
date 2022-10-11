package com.devforfun.mvidemo.main.model.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainModel(
    val cars: List<Car> = listOf(),
    val selectedCar: Car? = null
): Parcelable

@Parcelize
data class Car(
    val emoji: String,
    val title: String,
) : Parcelable
