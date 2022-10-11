package com.devforfun.mvidemo.main.model.repository

import com.devforfun.mvidemo.main.model.models.Car
import javax.inject.Inject

class MainRepository @Inject constructor() {

    fun fetchCarList(): List<Car> = listOf(
        Car("\uD83D\uDE9A", "BMW Electric Car"),
        Car("\uD83D\uDE98", "BMW Hybrid Car"),
        Car("\uD83D\uDE99", "BMW Normal Car"),
        Car("\uD83D\uDE9B", "BMW Electric Car"),
        Car("\uD83D\uDE9C", "BMW Hybrid Car"),
        Car("\uD83D\uDE8C", "BMW Normal Car"),
        Car("\uD83D\uDE93", "BMW Electric Car"),
    )
}
