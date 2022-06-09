package com.ximikat.mafiarating.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel

@Composable
fun GamesListScreenCompose(viewModel: GamesListViewModel) {

    val state = viewModel.mainState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All games")
                })
        }
    ) {

        val games = state.value.games
        val numMafiaWins = games.count { it.winningTeam == Team.BLACK }
        val numCiviliansWins = games.size - numMafiaWins

        Column(
            Modifier.scrollable(rememberScrollState(), Orientation.Vertical)
        ) {

            if (games.isNotEmpty()) {
                Row(
                    // modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Box(Modifier.padding(16.dp)) {
                        DonutChart(
                            values = numMafiaWins to numCiviliansWins,
                            colors = Color.Black to Color.Red, size = 64.dp
                        )
                    }
//                    Text(
//                        text = "Mafia $numMafiaWins : $numCiviliansWins Civilians",
//                        modifier = Modifier.height(64.dp),
//                        fontSize = 16.sp,
//                        textAlign = Alignment.CenterVertically
//                    )
                }
            }

            GamesListCompose(
                games = games,
                selectedGame = state.value.selectedGame,
                { viewModel.toggleGameSelection(it) },
                { viewModel.deleteGame(it) }
            )

        }

    }

}

@Composable
fun AddGameFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(onClick) {
        Icon(Icons.Filled.Add, "")
    }
}

@Composable
fun DonutChart(values: Pair<Int, Int>, colors: Pair<Color, Color>, size: Dp) {

    val total = values.first + values.second
    val proportion = values.first.toFloat() / total

    val angle = 360f * proportion

    BoxWithConstraints(
        modifier = Modifier
            .width(size)
            .height(size)
    ) {
        val padding = 0.3f * constraints.maxHeight

        Canvas(
            Modifier.size(size)
        ) {

            drawPie(
                colors.first, 0f, angle, Size(
                    constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat()
                ),
                padding
            )

            drawPie(
                colors.second, angle, 360f - angle, Size(
                    constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat()
                ),
                padding
            )

        }

    }

}

private fun DrawScope.drawPie(
    color: Color,
    startAngle: Float,
    sweepAngle: Float,
    size: Size,
    padding: Float
): Path {

    return Path().apply {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            size = size,
            style = Stroke(width = 25f),
            topLeft = Offset(padding / 2, padding / 2)
        )
    }
}