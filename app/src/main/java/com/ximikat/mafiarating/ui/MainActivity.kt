package com.ximikat.mafiarating.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.ui.theme.MafiaRatingTheme
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import com.ximikat.mafiarating.ui.viewmodel.PlayersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MafiaRatingTheme {

                val gamesViewModel = viewModel<GamesListViewModel>()
                val playersViewModel = viewModel<PlayersListViewModel>()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    Scaffold(
                        Modifier.fillMaxSize(),
                        topBar = {},
                        bottomBar = { BottomNavigationBar(navigationController) }
                    ) {
                        NavHost(navController = navigationController,
                            startDestination = NavigationItem.GamesScreen.route
                        ) {
                            composable(NavigationItem.PlayersScreen.route) {
                                PlayersListCompose(playersViewModel.value)
                            }
                            composable(NavigationItem.GamesScreen.route) {
                                GamesListCompose(gamesViewModel.value)
                            }
                        }
                    }
                }

            }
        }
    }
}