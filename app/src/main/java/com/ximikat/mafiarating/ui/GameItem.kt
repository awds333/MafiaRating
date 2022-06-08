package com.ximikat.mafiarating.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Team

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameItem(game: Game, isSelected: Boolean, onClick: () -> Unit) {

    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row {
                Icon(
                    Icons.Rounded.Lock,
                    "Winning team",
                    tint = if (game.winningTeam == Team.BLACK) Color.Black else Color.Red
                )
                Text(
                    text = if (game.winningTeam == Team.BLACK) {
                        "Mafia win!"
                    } else {
                        "Game suck"
                    }
                )
            }

            if (isSelected) {
                Column {
                    game.players.forEachIndexed { index, (player, points) ->
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // TODO: Fix overlap of two text fields
                            Text(
                                text = "${index + 1}. ${player.nickname}",
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                text = "%+.2f".format(points),
                                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                                color = if (points > 0) Color.Green else Color.Red,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }

        }
    }

}