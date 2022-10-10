package com.devforfun.mvidemo.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devforfun.mvidemo.main.model.repository.MainRepository
import com.devforfun.mvidemo.main.view.MainSideEffect
import com.devforfun.mvidemo.main.view.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository,
): ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(
        initialState = MainState(""),
        savedStateHandle = savedStateHandle
    ) {
        loadData()
    }

    private fun loadData() {
       
    }

    fun onAction(nextActionClick: MainAction) {
        when (nextActionClick) {
            NextActionClick -> {//navigate to next screen }
            }
        }
    }
}
