package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GamesListViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(GamesListState(0))
    val mainState: StateFlow<GamesListState>
        get() = _mainState

}