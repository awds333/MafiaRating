package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerStatisticsViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(PlayerStatisticsState(Player(""), emptyList()))
    val mainState = _mainState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            gamesRepository.getGames().collect {
                _mainState.value = _mainState.value.copy(allGames = it)
            }
        }
    }

    fun setPlayerFilter(player: Player) {
        _mainState.value = _mainState.value.copy(player = player)
    }

}