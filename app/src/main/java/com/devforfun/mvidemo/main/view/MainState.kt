package com.devforfun.mvidemo.main.view

import android.os.Parcelable
import com.devforfun.mvidemo.main.model.models.MainModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainState(val mainModel: MainModel): Parcelable

