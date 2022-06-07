package com.ximikat.mafiarating.model.domain

import java.util.*

data class Game(
    val players: List<Pair<Player, Double>>,
    val maf1: Int, val maf2: Int,
    val sheriff: Int, val don: Int,
    val winningTeam: Team,
    val date: Date
)

enum class Role {
    CIVILIANS, MAFIA, SHERIFF, DON
}

enum class Team {
    RED, BLACK
}
