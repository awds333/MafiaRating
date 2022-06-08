package com.ximikat.mafiarating.ui.viewmodel

import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Player

data class PlayerStatisticsState(
    val player: Player,
    val games: List<Game>,
    val selectedGame: Game? = null
)