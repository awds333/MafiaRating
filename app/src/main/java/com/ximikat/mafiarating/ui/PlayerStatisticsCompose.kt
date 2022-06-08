package com.ximikat.mafiarating.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ximikat.mafiarating.ui.viewmodel.PlayerStatisticsViewModel

@Composable
fun PlayerStatisticsCompose(viewModel: PlayerStatisticsViewModel) {

    // TODO: Statistics

    val state = viewModel.mainState.collectAsState()
    GamesListCompose(
        games = state.value.games,
        selectedGame = state.value.selectedGame,
    ) {
        viewModel.toggleGameSelection(it)
    }

}