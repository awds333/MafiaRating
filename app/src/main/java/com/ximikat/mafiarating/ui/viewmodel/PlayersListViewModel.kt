package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ximikat.mafiarating.repository.PlayersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayersListViewModel(private val playersRepository: PlayersRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(PlayersListState(0))
    val mainState: StateFlow<PlayersListState>
        get() = _mainState

}