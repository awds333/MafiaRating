package com.ximikat.mafiarating.ui.viewmodel

import com.ximikat.mafiarating.model.domain.Game

data class GamesListState(
    val games: List<Game>,
    val selectedGame: Game? = null
)