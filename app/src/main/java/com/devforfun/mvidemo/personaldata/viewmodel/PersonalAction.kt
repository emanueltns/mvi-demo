package com.devforfun.mvidemo.personaldata.viewmodel

sealed class PersonalDataAction

object BackActionClick: PersonalDataAction()
data class InputFirstNameActionClick(val input: String): PersonalDataAction()
data class InputSecondNameActionClick(val input: String): PersonalDataAction()
