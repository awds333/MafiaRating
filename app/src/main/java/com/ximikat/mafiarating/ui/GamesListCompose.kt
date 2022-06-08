package com.ximikat.mafiarating.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel

@Composable
fun GamesListCompose(viewModel: GamesListViewModel) {

    val games = viewModel.mainState.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(games.value.games) {
            GameItem(game = it, it == games.value.selectedGame) {
                viewModel.toggleGameSelection(it)
            }
        }
    }

}