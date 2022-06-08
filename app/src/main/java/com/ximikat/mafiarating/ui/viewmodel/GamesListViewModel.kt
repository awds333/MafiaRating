package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamesListViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(GamesListState(emptyList()))
    val mainState = _mainState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            gamesRepository.getGames().collect {
                _mainState.value = _mainState.value.copy(games = it)
            }
        }
    }

    fun toggleGameSelection(game: Game) {
        _mainState.value = if (_mainState.value.selectedGame == game) {
            _mainState.value.copy(selectedGame = null)
        } else {
            _mainState.value.copy(selectedGame = game)
        }
    }

}