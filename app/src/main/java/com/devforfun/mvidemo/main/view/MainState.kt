package com.devforfun.mvidemo.main.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainState(val str:String): Parcelable
