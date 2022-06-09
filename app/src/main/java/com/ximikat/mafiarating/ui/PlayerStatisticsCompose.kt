package com.ximikat.mafiarating.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ximikat.mafiarating.ui.viewmodel.PlayerStatisticsViewModel

@Composable
fun PlayerStatisticsCompose(viewModel: PlayerStatisticsViewModel) {

    val state = viewModel.mainState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.value.player.nickname)
                }
            )
        }
    ) {

        // TODO: Statistics

        GamesListCompose(
            games = state.value.games,
            selectedGame = state.value.selectedGame,
            { viewModel.toggleGameSelection(it) },
            { viewModel.deleteGame(it) }
        )

    }

}