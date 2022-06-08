package com.ximikat.mafiarating.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navigationController: NavHostController) {

    val items = listOf(NavigationItem.GamesScreen, NavigationItem.PlayersScreen)

    BottomNavigation() {

        items.forEach {
            BottomNavigationItem(
                // TODO: Colorize
                icon = { Icon(Icons.Default.Favorite, it.title) },
                label = { Text(text = it.title) },
                selected = navigationController.currentDestination?.route == it.route,
                onClick = {
                    navigationController.navigate(it.route) { launchSingleTop = true }
                }
            )
        }

    }

}