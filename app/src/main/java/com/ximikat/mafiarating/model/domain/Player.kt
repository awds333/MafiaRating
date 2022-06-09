package com.ximikat.mafiarating.model.domain

data class Player(
    val nickname: String
)

fun Player.won(): Pair<Player, Double> {
    return Pair(this, 1.0)
}

fun Player.lost(): Pair<Player, Double> {
    return Pair(this, 0.0)
}