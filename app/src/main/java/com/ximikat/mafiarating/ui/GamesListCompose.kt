package com.ximikat.mafiarating.ui

import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun GamesListCompose() {

    val viewModel = viewModel<GamesListViewModel>()


}

@Composable
fun GameItem(game: Game, onClick: () -> Unit) {
    Card() {

    }
}