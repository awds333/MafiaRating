package com.ximikat.mafiarating.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel

@Composable
fun GamesListScreenCompose(viewModel: GamesListViewModel) {

    // TODO: Statistics

    val state = viewModel.mainState.collectAsState()
    GamesListCompose(
        games = state.value.games,
        selectedGame = state.value.selectedGame
    ) {
        viewModel.toggleGameSelection(it)
    }

}

@Composable
fun AddGameFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(onClick) {
        Icon(Icons.Filled.Add, "")
    }
}