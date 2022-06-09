package com.ximikat.mafiarating.ui

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.ui.viewmodel.ConstructionStep
import com.ximikat.mafiarating.ui.viewmodel.GameConstructionViewModel
import com.ximikat.mafiarating.ui.viewmodel.isMafia
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun GameConstructionScreenCompose(
    viewModel: GameConstructionViewModel,
    navHostController: NavHostController
) {

    val state = viewModel.mainState.collectAsState()

    val context = LocalContext.current
    val mDatePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                viewModel.setDate(Date(mYear, mMonth, mDayOfMonth))
            },
            state.value.date.year,
            state.value.date.month,
            state.value.date.day
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = when (state.value.currentStep) {
                        ConstructionStep.NicknameInputStep -> "Input Nicknames"
                        ConstructionStep.MafiaInputStep -> "Select Mafia"
                        ConstructionStep.SheriffDonAndInputStep -> "Select Don & Sheriff"
                        ConstructionStep.FinalInputStep -> "Select Wining Team"
                    }
                )}
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            val itemView: @Composable (Int, String) -> Unit =
                when (state.value.currentStep) {
                    ConstructionStep.NicknameInputStep -> { index, nickname ->
                        TextField(
                            value = nickname,
                            onValueChange = { viewModel.setNickname(index, it) },
                            placeholder = { Text(text = "Player ${index + 1}") },
                            keyboardOptions = KeyboardOptions.Default,
                            singleLine = true
                        )
                    }
                    ConstructionStep.MafiaInputStep -> { index, nickname ->
                        TextField(
                            value = nickname,
                            readOnly = true,
                            onValueChange = {},
                            trailingIcon = {
                                Switch(
                                    checked = state.value.isMafia(index),
                                    onCheckedChange = {
                                        viewModel.toggleMafiaButton(index)
                                    }
                                )
                            }
                        )
                    }
                    ConstructionStep.SheriffDonAndInputStep -> { index, nickname ->
                        with(state.value) {
                            TextField(
                                value = nickname,
                                readOnly = true,
                                onValueChange = {},
                                trailingIcon = {
                                    Box(modifier =
                                    Modifier.clickable {
                                        viewModel.toggleSpatialRoll(index)
                                    }) {
                                        if (don == index || sheriff == index) {
                                            Icon(
                                                Icons.Filled.Star,
                                                contentDescription = "",
                                                tint = if (isMafia(index)) Color.Black else Color.Red,
                                            )
                                        } else if ((isMafia(index) && don == null) || (isMafia(index).not() && sheriff == null)) {
                                            Icon(
                                                Icons.Outlined.Star,
                                                contentDescription = "",
                                                tint = if (isMafia(index)) Color.Black else Color.Red
                                            )
                                        }
                                    }
                                })
                        }
                    }
                    ConstructionStep.FinalInputStep -> { index, nickname ->
                        with(state.value) {
                            TextField(
                                value = nickname,
                                readOnly = true,
                                onValueChange = {},
                                trailingIcon = {
                                    if (don == index || sheriff == index) {
                                        Icon(Icons.Filled.Star, contentDescription = "")
                                    }
                                })
                        }
                    }
                }

            state.value.playerNicknames.forEachIndexed { index, nickname ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemView(index, nickname)
                }
            }

            if (state.value.currentStep == ConstructionStep.FinalInputStep) {

                Row {
                    Text(text = "Wining team")
                    with(state.value) {
                        Box(modifier = Modifier.clickable {
                            viewModel.toggleWinningTeam(Team.RED)
                        }) {
                            Icon(
                                if (winningTeam == Team.RED) Icons.Filled.Star else Icons.Outlined.Star,
                                tint = Color.Red,
                                contentDescription = ""
                            )
                        }
                        Box(modifier = Modifier.clickable {
                            viewModel.toggleWinningTeam(Team.BLACK)
                        }) {
                            Icon(
                                if (winningTeam == Team.BLACK) Icons.Filled.Star else Icons.Outlined.Star,
                                tint = Color.Black,
                                contentDescription = ""
                            )
                        }
                    }
                }

                val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        mDatePickerDialog.show()
                    }) {
                    Text(text = formatter.format(state.value.date))
                }

            }

            val (enabled, nextStep) =
                when (state.value.currentStep) {
                    ConstructionStep.NicknameInputStep ->
                        state.value.allNicknamesChosen to ConstructionStep.MafiaInputStep
                    ConstructionStep.MafiaInputStep ->
                        state.value.mafiaSelected to ConstructionStep.SheriffDonAndInputStep
                    ConstructionStep.SheriffDonAndInputStep ->
                        state.value.donAndSheriffSelected to ConstructionStep.FinalInputStep
                    ConstructionStep.FinalInputStep ->
                        state.value.winningTeamSelected to ConstructionStep.FinalInputStep
                }

            val finalStep = state.value.currentStep == ConstructionStep.FinalInputStep

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                onClick = {
                    if (finalStep) {
                        viewModel.saveGame()
                        navHostController.popBackStack()
                    } else {
                        viewModel.setCurrentStep(nextStep)
                    }
                }
            ) {
                Text(text = if (finalStep) "Save game" else "Continue")
            }

        }

    }

}