package com.ximikat.mafiarating.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ximikat.mafiarating.database.GameDao
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.repository.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class GameConstructionViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _mainState = MutableStateFlow(GameConstructionState())
    val mainState: StateFlow<GameConstructionState>
        get() = _mainState

    fun setNickname(index: Int, nickname: String) {
        val nicknameIndex = _mainState.value.playerNicknames.indexOf(nickname)
        _mainState.value = _mainState.value.copy(
            playerNicknames = _mainState.value.playerNicknames
                .toMutableList().apply {
                    this[index] = nickname
                    if (nicknameIndex != -1) this[nicknameIndex] = ""
                }
        )
    }

    fun toggleMafiaButton(index: Int) {
        _mainState.value = _mainState.value.copy(
            mafia = if (_mainState.value.mafia.contains(index)) {
                mainState.value.mafia.toMutableList().apply { this.remove(index) }
            } else if (_mainState.value.mafia.size == 3) {
                _mainState.value.mafia.drop(1) + index
            } else {
                _mainState.value.mafia + index
            }
        )
    }

    fun toggleSpatialRoll(index: Int) {
        if (_mainState.value.isMafia(index)) {
            _mainState.value = _mainState.value.copy(
                don = if (_mainState.value.don == index) {
                    null
                } else {
                    index
                }
            )
        } else {
            _mainState.value = _mainState.value.copy(
                sheriff = if (_mainState.value.sheriff == index) {
                    null
                } else {
                    index
                }
            )
        }
    }

    fun toggleWinningTeam(team: Team) {
        _mainState.value = _mainState.value.copy(
            winningTeam = if (_mainState.value.winningTeam == team) {
                null
            } else team
        )
    }

    fun setDate(date: Date) {
        _mainState.value = _mainState.value.copy(date = date)
    }

    fun setCurrentStep(step: ConstructionStep) {
        _mainState.value = _mainState.value.copy(currentStep = step)
    }

    fun saveGame() {
        with(_mainState.value) {
            val mafiaDonExcluded: List<Int> = mafia.toMutableList().apply { remove(don) }
            val game = Game(
                entries = playerNicknames.mapIndexed { index, nickname ->
                    Pair(Player(nickname), if (isWinning(index)) 1.0 else 0.0)
                },
                maf1 = mafiaDonExcluded[0],
                maf2 = mafiaDonExcluded[1],
                sheriff = sheriff!!,
                don = don!!,
                winningTeam = winningTeam!!,
                date = date
            )
            viewModelScope.launch {
                gamesRepository.addGame(game)
            }
        }
        reset()
    }

    fun reset() {
        _mainState.value = GameConstructionState()
    }

}