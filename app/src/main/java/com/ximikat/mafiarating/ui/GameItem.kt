package com.ximikat.mafiarating.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ximikat.mafiarating.R
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Team

@Composable
fun GameItem(game: Game, isSelected: Boolean, onClick: () -> Unit, onDelete: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Row {
                Icon(
                    painterResource(id = R.drawable.ic_crown_circle), "",
                    tint = if (game.winningTeam == Team.BLACK) Color.Black else Color.Red
                )
                Text(
                    text = if (game.winningTeam == Team.BLACK) {
                        "Mafia won"
                    } else {
                        "Civilians won"
                    },
                    fontSize = 18.sp
                )
                if (isSelected) {
                    Box(
                        modifier = Modifier.clickable { onDelete() }
                    ) {
                        Icon(Icons.Default.Delete, "")
                    }
                }
            }

            if (isSelected) {
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    game.entries.forEachIndexed { index, (player, points) ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${index + 1}. ${player.nickname}",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

        }
    }

}