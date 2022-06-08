package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerStatisticsViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(PlayerStatisticsState(Player(""), emptyList()))
    val mainState = _mainState.asStateFlow()

    private var allGames = emptyList<Game>()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            gamesRepository.getGames().collect {
                allGames = it
                _mainState.value = _mainState.value.copy(
                    games = allGames.filter { game -> game.containsPlayer(_mainState.value.player) }
                )
            }
        }
    }

    fun setPlayerFilter(player: Player) {
        _mainState.value = _mainState.value.copy(
            player = player,
            games = allGames.filter { game -> game.containsPlayer(_mainState.value.player) },
            selectedGame = null
        )
    }

    fun toggleGameSelection(game: Game) {
        _mainState.value = if (_mainState.value.selectedGame == game) {
            _mainState.value.copy(selectedGame = null)
        } else {
            _mainState.value.copy(selectedGame = game)
        }
    }

}