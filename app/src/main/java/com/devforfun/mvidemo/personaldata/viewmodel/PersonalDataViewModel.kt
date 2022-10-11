package com.devforfun.mvidemo.personaldata.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devforfun.mvidemo.personaldata.view.NavigateBack
import com.devforfun.mvidemo.personaldata.view.PersonalDataSideEffect
import com.devforfun.mvidemo.personaldata.view.PersonalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PersonalDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ContainerHost<PersonalDataState, PersonalDataSideEffect>, ViewModel() {

    override val container = container<PersonalDataState, PersonalDataSideEffect>(
        initialState = PersonalDataState(""),
        savedStateHandle = savedStateHandle
    ) {
        loadData()
    }

    fun loadData() {
        //todo load personal data
    }

    fun onAction(action: PersonalDataAction) {
        when (action) {
            is BackActionClick -> {
                navigateBack()
            }
            is InputFirstNameActionClick -> {
                onFirsNameInput(action.input)
            }
            is InputSecondNameActionClick -> {
                onSecondNameInput(action.input)
            }
        }
    }

    fun onFirsNameInput(firstName: String) = intent {
        reduce {
            state.copy(
                firstName = firstName
            )
        }
    }

    fun onSecondNameInput(secondName: String) = intent {
        reduce {
            state.copy(
                secondName = secondName
            )
        }
    }

    fun navigateBack() = intent {
        postSideEffect(NavigateBack)
    }
}
