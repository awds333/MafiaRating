package com.ximikat.mafiarating.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ximikat.mafiarating.model.domain.Game
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.ui.theme.MafiaRatingTheme
import com.ximikat.mafiarating.ui.viewmodel.GamesListViewModel
import org.koin.androidx.compose.viewModel
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MafiaRatingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<GamesListViewModel>()
                    GamesListCompose(viewModel.value)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MafiaRatingTheme {
        GameItem(game = Game(emptyList(), 1, 2, 3, 3, Team.BLACK, Calendar.getInstance().time), {})
    }
}