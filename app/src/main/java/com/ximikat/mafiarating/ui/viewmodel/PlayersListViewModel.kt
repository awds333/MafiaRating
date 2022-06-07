package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayersListViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(PlayersListState(0))
    val mainState: StateFlow<PlayersListState>
        get() = _mainState

    init {
        viewModelScope.launch {
            gamesRepository.getGames().collect { games ->
                val playerSet = mutableSetOf<Player>()
                games.forEach {
                    it.players.forEach { (player, _) ->
                        playerSet.add(player)
                    }
                }
                _mainState.value = _mainState.value.copy(players = playerSet.toList())
            }
        }
    }

    fun selectPlayer(player: Player) {
        // TODO: Toggle navigation to PlayerViewModel
    }

}