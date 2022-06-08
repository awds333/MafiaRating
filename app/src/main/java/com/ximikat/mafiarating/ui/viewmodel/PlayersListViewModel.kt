package com.ximikat.mafiarating.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.repository.GamesRepository
import com.ximikat.mafiarating.ui.ScreenNavigationItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlayersListViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(PlayersListState(emptyList()))
    val mainState = _mainState.asStateFlow()

    private val _navigationAction = MutableSharedFlow<ScreenNavigationItem>()
    val navigationAction = _navigationAction.asSharedFlow()

    init {
        viewModelScope.launch {
            gamesRepository.getGames().collect { games ->
                val playerSet = mutableSetOf<Player>()
                games.forEach {
                    it.entries.forEach { (player, _) ->
                        playerSet.add(player)
                    }
                }
                _mainState.value = _mainState.value.copy(players = playerSet.toList())
            }
        }
    }

    fun selectPlayer(player: Player) {
        viewModelScope.launch {
            _navigationAction.emit(ScreenNavigationItem.PlayerScreen(player))
        }
    }

}