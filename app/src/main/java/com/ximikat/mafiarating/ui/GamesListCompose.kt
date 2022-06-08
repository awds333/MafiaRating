package com.ximikat.mafiarating.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import org.koin.androidx.compose.viewModel
import java.util.*

@Composable
fun GamesListCompose(viewModel: GamesListViewModel) {


    //val games = viewModel.value.mainState.collectAsState()
    LazyColumn(Modifier.fillMaxSize()) {
        val actualGames = listOf(
            Game(listOf(), 1, 1, 1, 1, Team.BLACK, Calendar.getInstance().time),
            Game(listOf(), 1, 1, 1, 1, Team.RED, Calendar.getInstance().time),
            Game(listOf(), 1, 1, 1, 1, Team.BLACK, Calendar.getInstance().time),
            Game(listOf(), 1, 1, 1, 1, Team.RED, Calendar.getInstance().time),
            Game(listOf(), 1, 1, 1, 1, Team.BLACK, Calendar.getInstance().time)
        )
        items(5) { i ->
            GameItem(game = actualGames[i]) {
                //viewModel.value.toggleGameSelection(game)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameItem(game: Game, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(text = if (game.winningTeam == Team.BLACK) {
            "Mafia win!"
        } else {
            "Game suck"
        })
    }
}