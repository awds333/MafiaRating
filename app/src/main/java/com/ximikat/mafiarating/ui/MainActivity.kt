package com.ximikat.mafiarating.ui

import android.os.Bundle
import android.view.WindowManager
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
import com.ximikat.mafiarating.model.domain.Player
import com.ximikat.mafiarating.ui.theme.MafiaRatingTheme
import com.ximikat.mafiarating.ui.viewmodel.GameConstructionViewModel
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayerStatisticsViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {

            MafiaRatingTheme {

                // TODO: Fix this?
                val gamesViewModel = viewModel<GamesListViewModel>()
                val playersViewModel = viewModel<PlayersListViewModel>()
                val statisticsViewModel = viewModel<PlayerStatisticsViewModel>()
                val gameConstructionViewModel = viewModel<GameConstructionViewModel>()

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
                                floatingActionButton = {
                                    AddGameFloatingButton {
                                        globalNavController.navigate(
                                            ScreenNavigationItem.GameCreationScreen.route
                                        )
                                    }
                                },
                                topBar = {},
                                bottomBar = { BottomNavigationBar(navigationController) }
                            ) {
                                NavHost(
                                    navController = navigationController,
                                    startDestination = BottomNavigationItem.GamesScreen.route
                                ) {
                                    composable(BottomNavigationItem.PlayersScreen.route) {
                                        PlayersListScreenCompose(playersViewModel.value)
                                    }
                                    composable(BottomNavigationItem.GamesScreen.route) {
                                        GamesListScreenCompose(gamesViewModel.value)
                                    }
                                }
                            }
                        }
                        composable(
                            ScreenNavigationItem.PlayerScreen.routeTemplate,
                            arguments = listOf(navArgument("nickname") {
                                type = NavType.StringType
                            })
                        ) {

                            val player = Player(it.arguments?.getString("nickname")!!)

                            LaunchedEffect(key1 = this@MainActivity) {
                                statisticsViewModel.value.setPlayerFilter(player)
                            }

                            PlayerStatisticsCompose(viewModel = statisticsViewModel.value)

                        }
                        composable(ScreenNavigationItem.GameCreationScreen.route) {
                            GameConstructionScreenCompose(gameConstructionViewModel.value, globalNavController)
                        }
                    }
                }

            }
        }
    }
}