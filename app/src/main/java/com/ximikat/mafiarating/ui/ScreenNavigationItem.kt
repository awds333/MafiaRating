package com.ximikat.mafiarating.ui

import androidx.compose.runtime.Composable
import com.ximikat.mafiarating.model.domain.Player

sealed class ScreenNavigationItem(val route: String) {
    object MainScreen : ScreenNavigationItem("main")
    object GameCreationScreen: ScreenNavigationItem("create")
    class PlayerScreen(player: Player) : ScreenNavigationItem("player/${player.nickname}") {
        companion object { const val routeTemplate: String = "player/{nickname}" }
    }
}
