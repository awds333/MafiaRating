package com.ximikat.mafiarating.ui

sealed class BottomNavigationItem(val route: String, val iconId: Int, val title: String) {
    object PlayersScreen : BottomNavigationItem("players", 0, "Players")
    object GamesScreen : BottomNavigationItem("games", 0, "Games")
}
