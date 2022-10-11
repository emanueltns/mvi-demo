package com.devforfun.mvidemo.personaldata.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalDataState(
    val firstName: String = "",
    val secondName: String = ""
    ) : Parcelable


