package com.ximikat.mafiarating.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel

@Composable
fun PlayersListScreenCompose(viewModel: PlayersListViewModel) {

    val state = viewModel.mainState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All players")
                })
        }
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(5.dp)
        ) {
            items(state.value.players) {
                Card(
                    modifier = Modifier.fillMaxWidth().clickable {
                        viewModel.selectPlayer(it)
                    }
                ) {
                    Text(
                        text = it.nickname,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }

        }

    }

}