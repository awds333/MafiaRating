package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameCreationViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(GameCreationState(0))
    val mainState: StateFlow<GameCreationState>
        get() = _mainState

}