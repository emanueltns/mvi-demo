package com.devforfun.mvidemo.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devforfun.mvidemo.main.model.models.Car
import com.devforfun.mvidemo.main.model.models.MainModel
import com.devforfun.mvidemo.main.model.repository.MainRepository
import com.devforfun.mvidemo.main.view.MainSideEffect
import com.devforfun.mvidemo.main.view.MainState
import com.devforfun.mvidemo.main.view.NavigateToPersonalData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository,
): ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(
        initialState = MainState(MainModel(listOf())),
        savedStateHandle = savedStateHandle
    ) {
        loadData()
    }

    private fun loadData() = intent {
        val carList = mainRepository.fetchCarList()
        reduce {
            state.copy(
                mainModel = state.mainModel.copy(
                    cars = carList
                )
            )
        }
    }

    fun onAction(action: MainAction) {
        when (action) {
            is NextActionClick -> { moveToPersonalDataScreen() }
            is SelectedCarActionClick -> onCarSelected(action.car)
        }
    }

    fun moveToPersonalDataScreen() = intent {
        postSideEffect(NavigateToPersonalData)
    }

    fun onCarSelected(car: Car) = intent{
        reduce {
            state.copy(
                mainModel = state.mainModel.copy(
                    selectedCar = car
                )
            )
        }
    }
}
