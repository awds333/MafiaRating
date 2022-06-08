package com.ximikat.mafiarating.ui

sealed class NavigationItem(val route: String, val iconId: Int, val title: String) {
    object PlayersScreen : NavigationItem("players", 0, "Players")
    object GamesScreen : NavigationItem("games", 0, "Games")
}
