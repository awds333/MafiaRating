package com.ximikat.mafiarating.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import kotlin.math.log

@Composable
fun PlayersListScreenCompose(viewModel: PlayersListViewModel) {

    // TopAppBar() {}

    val players = viewModel.mainState.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(players.value.players) {
            Text(text = it.nickname, modifier = Modifier.clickable {
                viewModel.selectPlayer(it)
            })
        }

    }

}