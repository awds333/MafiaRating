package com.ximikat.mafiarating.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ximikat.mafiarating.ui.theme.MafiaRatingTheme
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            MafiaRatingTheme {

                val gamesViewModel = viewModel<GamesListViewModel>()
                val playersViewModel = viewModel<PlayersListViewModel>()

                val globalNavController = rememberNavController()

                LaunchedEffect(key1 = this@MainActivity) {
                    playersViewModel.value.navigationAction.collect {
                        globalNavController.navigate(it.route)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = globalNavController,
                        startDestination = ScreenNavigationItem.MainScreen.route
                    ) {
                        composable(ScreenNavigationItem.MainScreen.route) {
                            Scaffold(
                                Modifier.fillMaxSize(),
                                topBar = {},
                                bottomBar = { BottomNavigationBar(navigationController) }
                            ) {
                                NavHost(
                                    navController = navigationController,
                                    startDestination = BottomNavigationItem.GamesScreen.route
                                ) {
                                    composable(BottomNavigationItem.PlayersScreen.route) {
                                        PlayersListCompose(playersViewModel.value)
                                    }
                                    composable(BottomNavigationItem.GamesScreen.route) {
                                        GamesListCompose(gamesViewModel.value)
                                    }
                                }
                            }
                        }
                        composable(
                            ScreenNavigationItem.PlayerScreen.routeTemplate,
                            arguments = listOf(navArgument("nickname") { type = NavType.StringType })
                        ) {
                            getString()
                        }
                    }
                }

            }
        }
    }
}